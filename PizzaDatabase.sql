drop database PizzaData;
create database PizzaData;
drop user 'PizzaCook'@'localhost';
flush privileges;
create user 'PizzaCook'@'localhost' identified by 'pizzaisgood';
grant select, insert, update, delete, create, create view, drop,
 execute, references on PizzaData.* to 'PizzaCook'@'localhost';

use PizzaData;

drop table if exists Users;
drop table if exists History;

create table Users (
  username varchar(32) not null,
  password varchar(32) not null,
  name varchar(32) not null,
  zipCode char(5) not null,
  street varchar(32) not null,
  city varchar(32) not null,
  cardNum varchar(32) not null,
  constraint pkUsers primary key (username));

 create table History (
  username varchar(32) not null, 
  pizzaSize varchar(6) not null,
  pizzaCost varchar(6) not null,
  toppingCount varchar(2),
  toppingCost varchar(6),
  sideCount varchar(2),
  sideCost varchar(6),
  constraint pkHistory primary key (username),
  foreign key (username) references Users(username));
