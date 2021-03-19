BEGIN;

DROP TABLE IF EXISTS products CASCADE;
CREATE TABLE products (id bigserial PRIMARY KEY, title VARCHAR(255), cost int);
INSERT INTO products (title, cost) VALUES
('Milk', 50),
('Bread', 40),
('Apple', 70),
('Orange', 90),
('Banana', 60),
('Tea', 100),
('Cheese', 150),
('Sugar', 50);

DROP TABLE IF EXISTS persons CASCADE;
CREATE TABLE persons (id bigserial PRIMARY KEY, name VARCHAR(255));
INSERT INTO persons (name) VALUES
('Alex'),
('Bob'),
('Tom');

DROP TABLE IF EXISTS orders CASCADE;
CREATE TABLE orders (id bigserial PRIMARY KEY, persons_id bigint,
                      FOREIGN KEY (persons_id) REFERENCES persons (id));
INSERT INTO orders (id, persons_id) VALUES
(1, 1),
(2, 1),
(3, 3),
(4, 3),
(5, 2);


DROP TABLE IF EXISTS order_details CASCADE;
CREATE TABLE order_details (id bigserial PRIMARY KEY, order_id bigint , product_id bigint, quantity int,
                            FOREIGN KEY (product_id) REFERENCES products (id));
INSERT INTO order_details (order_id, product_id, quantity) VALUES
(1, 1, 2),
(1, 2, 1),
(1, 3, 3),
(2, 1, 1),
(3, 2, 2),
(3, 4, 2),
(3, 5, 1),
(4, 5, 1),
(4, 6, 3),
(4, 7, 1),
(5, 3, 3);

COMMIT;