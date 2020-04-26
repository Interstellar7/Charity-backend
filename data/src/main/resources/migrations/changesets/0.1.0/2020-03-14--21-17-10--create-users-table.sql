CREATE TABLE users
(
    user_id SERIAL PRIMARY KEY,
    first_name VARCHAR(50) NOT NULL,
    last_name VARCHAR(50) NOT NULL,
    username VARCHAR(64) NOT NULL,
    password CHAR(80) NOT NULL,
    deleted BOOLEAN NOT NULL DEFAULT FALSE,
    created_at TIMESTAMP NOT NULL,
    updated_at TIMESTAMP NULL,
    created_by INTEGER NULL,
    updated_by INTEGER NULL
);
