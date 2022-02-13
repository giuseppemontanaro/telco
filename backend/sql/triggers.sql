-- mat view
CREATE TABLE service_package_view(service_pkg_id INT, total INT, total_val_per_12 INT, total_val_per_24 INT, total_val_per_36 INT, sales_pkg_only DOUBLE, sales_with_opt_prods DOUBLE, avg_prods DOUBLE);

CREATE TABLE insolvent_users_view(id int AUTO_INCREMENT, username VARCHAR(45), email VARCHAR(45), PRIMARY KEY(id));

CREATE TABLE suspended_order_view(id int AUTO_INCREMENT, total float, subscription_date datetime, date datetime, PRIMARY KEY(id));

CREATE TABLE alert_view(user_id INT, username VARCHAR(45), email VARCHAR(45), amount DOUBLE, last_rejection_date DATETIME);

CREATE TABLE best_seller_view(name VARCHAR(50), purchases int);


-- trigger

DELIMITER $$
CREATE TRIGGER insolvent_users_trigger 
AFTER UPDATE ON Telco.user 
FOR EACH ROW 
BEGIN
	IF (new.isInsolvent = true)     
	THEN INSERT INTO insolvent_users_view VALUES (new.id, new.username, new.email);  
	END IF; 
END$$
DELIMITER ;


DELIMITER $$
CREATE TRIGGER update_purchase_trigger
AFTER UPDATE ON purchase 
FOR EACH ROW 
BEGIN  
    IF (NEW.isRejected = false)     
    THEN DELETE FROM suspended_order_view o WHERE o.id = NEW.id;
    END IF;
END$$
DELIMITER ;


DELIMITER $$
CREATE TRIGGER insert_purchase_trigger
AFTER INSERT ON purchase 
FOR EACH ROW 
BEGIN  
    DELETE FROM service_package_view spw WHERE spw.service_pkg_id = NEW.service_pkg_fk;
    INSERT INTO service_package_view   
    SELECT s.id as service_pkg_id, count(distinct o.id) as total,
        count(case p.month_number when 12 then 1 else null end) as total_val_per_12,
        count(case p.month_number when 24 then 1 else null end) as total_val_per_24,
        count(case p.month_number when 36 then 1 else null end) as total_val_per_36,
        sum(o.total) - sum(op.monthly_fee * p.month_number) as sales_pkg_only,   
        sum(o.total) as sales_with_opt_prods,   
        sum(case when op.id is not null then 1 else 0 end) / count(distinct o.id) avg_prods  
        FROM purchase o JOIN service_package s ON o.service_pkg_fk = s.id 
            LEFT JOIN order_product opo ON opo.order_fk = o.id 
            LEFT JOIN product op ON opo.product_fk = op.id
            JOIN validity_period p ON o.validity_period_fk = p.id  
        WHERE o.isRejected = false and s.id = NEW.service_pkg_fk
        GROUP BY s.id;

    IF (NEW.isRejected = true)     
    THEN INSERT INTO suspended_order_view VALUES(NEW.id, NEW.total, NEW.subscription_date, NEW.date);  
    END IF;     

    DELETE FROM alert_view WHERE user_id = NEW.user_foreignk;
    INSERT INTO alert_view
       SELECT u.id, u.username, u.email, sum(o.total), max(o.date)
       FROM purchase o, user u
       WHERE o.user_foreignk = u.id and u.isInsolvent = true
       GROUP BY u.id
       HAVING count(*) >= 3;

    DELETE FROM best_seller_view bsw;
    INSERT INTO best_seller_view
      SELECT p.name, count(*) as num_orders
      FROM purchase o, order_product op, product p
      WHERE op.order_fk = o.id and op.product_fk = p.id
      GROUP BY p.name
      ORDER BY num_orders DESC
      LIMIT 1;
END$$
DELIMITER ;
