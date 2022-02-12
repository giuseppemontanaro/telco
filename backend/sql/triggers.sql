-- mat view
CREATE TABLE service_package_view(service_pkg_id INT, total INT, total_val_per_12 INT, total_val_per_24 INT, total_val_per_36 INT, sales_pkg_only DOUBLE, sales_with_opt_prods DOUBLE, avg_prods DOUBLE);

CREATE TABLE insolvent_users_view(id int AUTO_INCREMENT, username VARCHAR(45), email VARCHAR(45), PRIMARY KEY(id));

CREATE TABLE suspended_order_view(id int AUTO_INCREMENT, total float, subscription_date datetime, date datetime, PRIMARY KEY(id));

CREATE TABLE alert_view(user_id INT, username VARCHAR(45), email VARCHAR(45), amount DOUBLE, last_rejection_date DATETIME);

CREATE TABLE best_seller_view(name VARCHAR(50), purchases int);


-- trigger
CREATE TRIGGER service_package_trigger  
AFTER INSERT ON purchase 
FOR EACH ROW
DELETE FROM service_package_view spw WHERE spw.service_pkg_id = NEW.service_pkg_fk;
INSERT INTO service_package_view   
SELECT s.id as service_pkg_id, count(*) as total,
    count(case p.month_number when 12 then 1 else null end) as total_val_per_12,
    count(case p.month_number when 24 then 1 else null end) as total_val_per_24,
    count(case p.month_number when 36 then 1 else null end) as total_val_per_36,
    sum(p.monthly_fee) as sales_pkg_only,   
    sum(p.monthly_fee) + sum(op.monthly_fee) sales_with_opt_prods,   
    count(*) / count(distinct s.id) avg_prods  
    FROM purchase o, service_package s, product op, validity_period p, order_product opo  
    WHERE o.service_pkg_fk = s.id and opo.product_fk = op.id and opo.order_fk = o.id and o.validity_period_fk = p.id  
    GROUP BY s.id


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
CREATE TRIGGER suspended_order_trigger 
AFTER INSERT ON purchase 
FOR EACH ROW 
BEGIN  
	IF (new.isRejected = true)     
    THEN INSERT INTO suspended_order_view VALUES(id, total, subscription_date, date);  
    END IF; 
END$$
DELIMITER ;


CREATE TRIGGER alert_trigger 
AFTER INSERT ON purchase 
FOR EACH ROW INSERT INTO alert_view
   SELECT u.id, u.username, u.email, sum(o.total), max(o.date)
   FROM purchase o, user u
   WHERE o.user_foreingk = u.id and u.isInsolvent = true
   GROUP BY u.id
   HAVING count(*) >= 3


CREATE TRIGGER bestseller_trigger
AFTER INSERT ON purchase 
FOR EACH ROW 
DELETE FROM best_seller_view bsw;
INSERT INTO best_seller_view
  SELECT p.name, count(*) as num_orders
  FROM purchase o, order_product op, product p
  WHERE op.order_fk = o.id and op.product_fk = p.id
  GROUP BY p.name
  ORDER BY num_orders DESC
  LIMIT 1
