-- mat view
CREATE TABLE service_package_view(service_pkg_id INT, total INT, total_val_per_12 INT, total_val_per_24 INT, total_val_per_36 INT, sales_pkg_only DOUBLE, sales_with_opt_prods DOUBLE, avg_prods DOUBLE);

CREATE TABLE insolvent_users_view(user ddl);

CREATE TABLE suspended_order_view(order ddl);

CREATE TABLE alert_view(user_id INT, username VARCHAR(30), email VARCHAR(30), order_id INT, amount DOUBLE, last_rejection_date DATETIME);

CREATE TABLE best_seller_view(id INT, name VARCHAR(50));


-- trigger
CREATE TRIGGER service_package_trigger 
AFTER INSERT ON order
FOR EACH ROW 
DELETE FROM service_package_view spw WHERE spw.service_pkg_id = new.service_pkg_id
INSERT INTO service_package_view 
	SELECT s.id as service_pkg_id, count(*) as total, 
		count(case x. when 12 then 1 else null end) as total_val_per_12,
		count(case x. when 24 then 1 else null end) as total_val_per_24,
		count(case x. when 36 then 1 else null end) as total_val_per_36,
		sum(s.cost) as sales_pkg_only,
		sum(s.cost) + sum(op.cost) sales_with_opt_prods,
		count(*) / count(distinct s.service_pkg_id) avg_prods
	FROM order o, service_package s, optional_products op, validity_period p
	WHERE o.service_pkg_id = s.id and o.optional_products_fk = op.id and o.validity_period_fk = p.id
	GROUP BY s.id;


CREATE TRIGGER insolvent_users_trigger
AFTER UPDATE ON user
FOR EACH ROW
WHEN new.insolvent = true
INSERT INTO insolvent_users_view VALUES (new.id, new.username, new.email);


CREATE TRIGGER suspended_order_trigger
AFTER UPDATE ON order
FOR EACH ROW
WHEN new.is_rejected = true
INSERT INTO suspended_order VALUES (row);

user_id INT, username VARCHAR(30), email VARCHAR(30), order_id INT, amount DOUBLE, last_rejection_date DATETIME
CREATE TRIGGER alert_trigger
AFTER UPDATE ON order
FOR EACH ROW
INSERT INTO alert_view 
	SELECT u.id, u.username, u.email, sum(o.cost), max(o.date)
	FROM order o, user u
	WHERE o.user_fk = u.id and u.insolvent = true
	GROUP BY u.id
	HAVING count(*) >= 3;
