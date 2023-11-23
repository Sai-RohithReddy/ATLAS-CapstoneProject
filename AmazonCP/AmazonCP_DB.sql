CREATE DATABASE IF NOT EXISTS amazondb;

USE amazondb;

CREATE TABLE IF NOT EXISTS customers(
	customer_id INT PRIMARY KEY AUTO_INCREMENT, 
    first_name VARCHAR(20),
    last_name VARCHAR(20),
    customer_mobile_number LONG);
ALTER TABLE customers AUTO_INCREMENT = 101;    

CREATE TABLE IF NOT EXISTS products(
	product_id INT AUTO_INCREMENT PRIMARY KEY,
    product_name VARCHAR(20),
    product_prise DECIMAL(9, 2));
ALTER TABLE products AUTO_INCREMENT = 201; 
   
CREATE TABLE IF NOT EXISTS orders(
	order_id INT AUTO_INCREMENT PRIMARY KEY,
    product_id INT,
    customer_id INT,
    order_quantity INT,
    total_amount DECIMAL(9, 2),
    FOREIGN KEY(customer_id) REFERENCES customers(customer_id),
    FOREIGN KEY(product_id) REFERENCES products(product_id));
ALTER TABLE orders AUTO_INCREMENT = 301; 
    
INSERT INTO customers(first_name, last_name, customer_mobile_number) VALUES 
	("rohith", "reddy", 9876543210),
    ("sai", "ram", 8967452301);

INSERT INTO products(product_name, product_prise) VALUES 
	("football", 500.00),
    ("oven", 2000.00),
    ("freezer", 25000.00),
    ("camera", 5500.00),
    ("tv", 48000.00);

INSERT INTO orders(product_id, customer_id, order_quantity, total_amount) VALUES 
	(201, 101, 5, (SELECT product_prise FROM products WHERE product_id = 201) * 5),
    (202, 101, 2, (SELECT product_prise FROM products WHERE product_id = 202) * 2),
    (203, 101, 1, (SELECT product_prise FROM products WHERE product_id = 203) * 1),
    (204, 101, 4, (SELECT product_prise FROM products WHERE product_id = 204) * 4),
    (205, 101, 1, (SELECT product_prise FROM products WHERE product_id = 205) * 1),
    (201, 102, 3, (SELECT product_prise FROM products WHERE product_id = 201) * 3),
    (202, 102, 1, (SELECT product_prise FROM products WHERE product_id = 202) * 1),
    (203, 102, 1, (SELECT product_prise FROM products WHERE product_id = 203) * 1),
    (204, 102, 3, (SELECT product_prise FROM products WHERE product_id = 204) * 3),
    (205, 102, 1, (SELECT product_prise FROM products WHERE product_id = 205) * 1);
    
SELECT * FROM customers;
SELECT * FROM products;
SELECT * FROM orders;

SELECT * FROM customers 
	LEFT JOIN orders 
    ON customers.customer_id = orders.customer_id;
SELECT * 
	FROM products 
	LEFT JOIN orders 
    ON products.product_id = orders.product_id;

SELECT orders.order_id, customers.first_name, products.product_name, products.product_prise, orders.order_quantity, orders.total_amount 
	FROM customers 
    LEFT JOIN orders 
    ON customers.customer_id = orders.customer_id
    LEFT JOIN products
    ON products.product_id = orders.product_id;
    
SELECT * FROM products
	WHERE product_prise = (
	SELECT MAX(product_prise) FROM products);

SELECT product_name, SUM(order_quantity) AS total_sold
	FROM products
    LEFT JOIN orders
    ON products.product_id = orders.product_id
    GROUP BY products.product_name
    ORDER BY total_sold DESC
    LIMIT 1;
