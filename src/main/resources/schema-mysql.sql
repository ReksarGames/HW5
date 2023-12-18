DROP TABLE IF EXISTS customers CASCADE;
CREATE TABLE customers (
                               id SERIAL PRIMARY KEY,
                               name VARCHAR(250) NOT NULL,
                               email VARCHAR(250) NOT NULL,
                               age INTEGER NOT NULL,
                               phone_numbers INTEGER NOT NULL,
                               password VARCHAR(250) NOT NULL,
                               customer_id INTEGER references `employers` (id)
);

DROP TABLE IF EXISTS accounts CASCADE;
CREATE TABLE accounts (
                       id SERIAL PRIMARY KEY,
                       number VARCHAR(250) NOT NULL,
                       currency VARCHAR(3) NOT NULL,
                       balance NUMERIC (12,2) NOT NULL DEFAULT 0,
                       customer_id INTEGER REFERENCES customers (id),
                       account_id INTEGER references accounts (id)

);

DROP TABLE IF EXISTS employers CASCADE;
CREATE TABLE employers (
                       id SERIAL PRIMARY KEY,
                       name VARCHAR(250) NOT NULL,
                       address VARCHAR(250) NOT NULL
);

DROP TABLE IF EXISTS customerEmployment CASCADE;
CREATE TABLE customerEmployment (
                       id SERIAL PRIMARY KEY,
                       customer_id INTEGER REFERENCES customers (id) ,
                       employer_id INTEGER REFERENCES employers (id)
);
DROP TABLE IF EXISTS roles CASCADE;
CREATE TABLE roles (
    role_id SERIAL PRIMARY KEY,
    role_name VARCHAR(250) NOT NULL,
    customer_id INTEGER REFERENCES customers (id)
)
