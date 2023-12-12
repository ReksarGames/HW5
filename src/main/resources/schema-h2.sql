DROP TABLE IF EXISTS customers CASCADE;
CREATE TABLE public.customers (
                               id INT AUTO_INCREMENT  PRIMARY KEY,
                               name VARCHAR(250) NOT NULL,
                               email VARCHAR(250) NOT NULL,
                               age INT NOT NULL,
                               phone_numbers INT NOT NULL ,
                               password VARCHAR(250) NOT NULL,
                               customer_id INTEGER REFERENCES employers (id)

);

DROP TABLE IF EXISTS accounts CASCADE;
CREATE TABLE public.accounts (
                       id INTEGER AUTO_INCREMENT PRIMARY KEY,
                       number VARCHAR(250) NOT NULL,
                       currency VARCHAR(3) NOT NULL,
                       balance NUMERIC (12,2) NOT NULL DEFAULT 0,
                       customer_id INTEGER REFERENCES customers (id) ,
                       account_id INTEGER references accounts (id)
);

DROP TABLE IF EXISTS employers CASCADE;
CREATE TABLE public.employers (
                       id INTEGER AUTO_INCREMENT PRIMARY KEY,
                       name VARCHAR(250) NOT NULL,
                       address VARCHAR(250) NOT NULL
);

DROP TABLE IF EXISTS customerEmployment CASCADE;
CREATE TABLE public.customerEmployment (
                       id INTEGER AUTO_INCREMENT PRIMARY KEY,
                       customer_id INTEGER REFERENCES customers (id) ,
                       employer_id INTEGER REFERENCES employers (id)
);

