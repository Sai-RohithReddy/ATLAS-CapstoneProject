CREATE DATABASE IF NOT EXISTS amazoncp;

USE amazondcp;

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
    FOREIGN KEY(customer_id) REFERENCES customers(customer_id),
    FOREIGN KEY(product_id) REFERENCES products(product_id));
ALTER TABLE orders AUTO_INCREMENT = 301; 
    
CREATE TABLE IF NOT EXISTS sales(
	sales_id INT PRIMARY KEY AUTO_INCREMENT,
	product_id INT,
    customer_id INT,
	order_id INT,
    total_amount DECIMAL(9, 2),
	FOREIGN KEY(customer_id) REFERENCES customers(customer_id),
    FOREIGN KEY(product_id) REFERENCES products(product_id),
    FOREIGN KEY(order_id) REFERENCES orders(order_id));
ALTER TABLE sales AUTO_INCREMENT = 401;    
    
INSERT INTO customers(first_name, last_name, customer_mobile_number) VALUES 
	("rohith", "reddy", 9876543210),
    ("sai", "ram", 8967452301);

INSERT INTO products(product_name, product_prise) VALUES 
	("foot ball", 500.00),
    ("oven", 2000.00),
    ("freezer", 25000.00),
    ("camera", 5500.00),
    ("tv", 48000.00);
    
INSERT INTO orders(product_id, customer_id, order_quantity) VALUES
	(201, 101, 5),
    (202, 101, 2),
    (203, 101, 1),
    (204, 101, 4),
    (205, 101, 1),
    (201, 102, 3),
    (202, 102, 1),
    (203, 102, 1),
    (204, 102, 3),
    (205, 102, 1);

INSERT INTO sales(product_id, customer_id, order_id, total_amount) VALUES 
	(201, 101, 301, (SELECT product_prise FROM products WHERE product_id = 201) * (SELECT order_quantity FROM orders WHERE order_id = 301)),
    (202, 101, 302, (SELECT product_prise FROM products WHERE product_id = 202) * (SELECT order_quantity FROM orders WHERE order_id = 302)),
    (203, 101, 303, (SELECT product_prise FROM products WHERE product_id = 203) * (SELECT order_quantity FROM orders WHERE order_id = 303)),
    (204, 101, 304, (SELECT product_prise FROM products WHERE product_id = 204) * (SELECT order_quantity FROM orders WHERE order_id = 304)),
    (205, 101, 305, (SELECT product_prise FROM products WHERE product_id = 205) * (SELECT order_quantity FROM orders WHERE order_id = 305)),
    (201, 102, 306, (SELECT product_prise FROM products WHERE product_id = 201) * (SELECT order_quantity FROM orders WHERE order_id = 306)),
    (202, 102, 307, (SELECT product_prise FROM products WHERE product_id = 202) * (SELECT order_quantity FROM orders WHERE order_id = 307)),
    (203, 102, 308, (SELECT product_prise FROM products WHERE product_id = 203) * (SELECT order_quantity FROM orders WHERE order_id = 308)),
    (204, 102, 309, (SELECT product_prise FROM products WHERE product_id = 204) * (SELECT order_quantity FROM orders WHERE order_id = 309)),
    (205, 102, 310, (SELECT product_prise FROM products WHERE product_id = 205) * (SELECT order_quantity FROM orders WHERE order_id = 310));
    
SELECT * FROM customers;
SELECT * FROM products;
SELECT * FROM orders;
SELECT * FROM sales;

SELECT * FROM customers 
	LEFT JOIN orders 
    ON customers.customer_id=orders.customer_id;
SELECT * 
	FROM products 
	LEFT JOIN orders 
    ON products.product_id=orders.product_id;

SELECT orders.order_id, customers.first_name, products.product_name, products.product_prise, orders.order_quantity
	FROM customers 
    LEFT JOIN orders 
    ON customers.customer_id = orders.customer_id
    LEFT JOIN products
    ON products.product_id = orders.product_id;
    
SELECT orders.order_id, customers.first_name, products.product_name, products.product_prise, orders.order_quantity, sales.total_amount
	FROM customers
    LEFT JOIN sales
    ON customers.customer_id = sales.customer_id
    LEFT JOIN products
    ON products.product_id = sales.product_id
    LEFT JOIN orders
    ON orders.order_id = sales.order_id
    WHERE customers.first_name = "rohith";