drop schema if exists backend cascade;
create schema backend;
set search_path to backend, public;

CREATE TABLE users
(
    id serial not null constraint users_pkey primary key,
    username VARCHAR(45) NOT NULL,
    password VARCHAR(45) NOT NULL,
    enabled INT NOT NULL
);

CREATE TABLE authorities
(
    id serial not null constraint authorities_pkey primary key,
    username varchar(45) NOT NULL,
    authority varchar(45) NOT NULL
);

INSERT INTO users (username, password, enabled) VALUES ('tester', '12345', '1');
INSERT INTO authorities (username, authority) VALUES ('tester', 'write');

CREATE TABLE customer
(
    id serial not null constraint customers_pkey primary key,
    email varchar(45) NOT NULL,
    pwd varchar(200) NOT NULL,
    role varchar(45) NOT NULL
);

INSERT INTO customer (email, pwd, role) VALUES ('tester@example.com', '54321', 'admin');
-- decoded password: 12345
INSERT INTO customer (email, pwd, role) VALUES ('admin@example.com', '$2a$12$NNKkweAuOE6/.0oJbEtAceCGVVjpEQ1khzS/hgmvV8C80aXIsl83u', 'admin');



