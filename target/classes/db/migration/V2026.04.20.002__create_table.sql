CREATE TABLE products.products (
    id VARCHAR(36) PRIMARY KEY,
    name VARCHAR(256) NOT NULL,
    price FLOAT NOT NULL,
    unit VARCHAR(255) NOT NULL
);