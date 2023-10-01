CREATE TABLE IF NOT EXISTS exchange_value (
    id INT PRIMARY KEY,
    currency_from VARCHAR(255),
    currency_to VARCHAR(255),
    conversion_multiple DECIMAL(10, 2),
    port INT
);