BEGIN;

DROP TABLE IF EXISTS product CASCADE;
CREATE TABLE product (id bigserial PRIMARY KEY, title VARCHAR(255), cost int);
INSERT INTO product (title, cost) VALUES
('Milk', 50),
('Bread', 40),
('Apple', 70),
('Orange', 90),
('Banana', 60),
('Tea', 100),
('Cheese', 150),
('Sugar', 50);

COMMIT;