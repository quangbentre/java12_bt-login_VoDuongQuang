CREATE DATABASE IF NOT EXISTS crm;

USE crm;

CREATE TABLE IF NOT EXISTS user (
	id 				int auto_increment,
    email			varchar(100) not null unique,
    password		varchar(255) not null,
    name 			varchar(100) not null,

    primary key(id)
);

INSERT INTO crm.user (email, password) VALUES ("test@mail.com", "1234");