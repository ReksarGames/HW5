BEGIN;

INSERT INTO customers (name, email, age, phone_numbers, password, customer_id) VALUES ('Ivan Petrenko', 'ivanpetrenko@mail.com', 22, 3, 'password', 1);
INSERT INTO customers (name, email, age, phone_numbers, password, customer_id) VALUES ('Oksana Petrenko', 'oksanapetrenko@mail.com', 21, 3, 'password', 2);
INSERT INTO customers (name, email, age, phone_numbers, password, customer_id) VALUES ('Petro Ivanenko', 'petroivanenko@mail.com', 23, 3, 'password', 3);
INSERT INTO customers (name, email, age, phone_numbers, password, customer_id) VALUES ('Natalya Ivanenko', 'natalyaivanenko@mail.com', 22, 3, 'password', 4);
INSERT INTO customers (name, email, age, phone_numbers, password, customer_id) VALUES ('Sergiy Stepanenko', 'sergiystepanenko@mail.com', 24, 3, 'password', 5);
INSERT INTO customers (name, email, age, phone_numbers, password, customer_id) VALUES ('Galyna Stepanenko', 'galynastepanenko@mail.com', 23, 3, 'password', 6);


INSERT INTO accounts
    (number, currency, balance, customer_id, account_id)
    VALUES
    ('82560eb9-c8f9-4be9-aaef-17fd129cec6e', 'UAH', 0,1, 1);

INSERT INTO accounts
    (number, currency, balance, customer_id, account_id)
    VALUES
    ('f02683e2-519e-487b-b855-5286182ad1d4', 'USD', 0,4, 2);

INSERT INTO accounts
    (number, currency, balance, customer_id, account_id)
    VALUES
    ('6295e6db-667e-4d1b-adc1-dce573b01942', 'UAH', 0,5, 3);

INSERT INTO accounts
    (number, currency, balance, customer_id, account_id)
    VALUES
    ('477b0882-84a5-41c3-87d8-a3b26cb7278f', 'EUR', 0,4, 4);

INSERT INTO accounts
    (number, currency, balance, customer_id, account_id)
    VALUES
    ('45d91b68-a4dd-4de3-88dd-13e106b12a0c', 'EUR', 0,2, 5);

INSERT INTO accounts
    (number, currency, balance, customer_id, account_id)
    VALUES
    ('4e204fcd-d296-483c-964c-74d0be4130bb', 'CHF', 0,5, 6);

INSERT INTO accounts
    (number, currency, balance, customer_id, account_id)
    VALUES
    ('9db430b11-6b5c-4d63-9663-4f59974ea04', 'USD', 0,1, 7);

INSERT INTO accounts
    (number, currency, balance, customer_id, account_id)
    VALUES
    ('4f3d9294-a79e-48b5-8109-c934727f8edf', 'UAH', 0,4,8);



INSERT INTO employers
    (name, address)
    VALUES
    ('Google', 'US. Calif');

INSERT INTO employers
     (name, address)
     VALUES
     ('Microsoft', 'US. New-Yo');

INSERT INTO employers
     (name, address)
     VALUES
     ('Boeing', 'US. Kent');

INSERT INTO customerEmployment
    (customer_id, employer_id)
    VALUES
    (1, 1);

INSERT INTO customerEmployment
    (customer_id, employer_id)
    VALUES
    (1, 2);

 INSERT INTO customerEmployment
     (customer_id, employer_id)
     VALUES
     (3, 3);

  INSERT INTO customerEmployment
      (customer_id, employer_id)
      VALUES
      (4, 3);

  INSERT INTO customerEmployment
      (customer_id, employer_id)
      VALUES
      (4, 1);

COMMIT;
