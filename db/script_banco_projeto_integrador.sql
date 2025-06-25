create database if not exists base;
use base;

create table if not exists user (
id bigint primary key auto_increment,
name varchar(200) ,
username varchar(100) not null,
password varchar(100) not null,
registration_date date 
);

create table if not exists payment(
id bigint primary key auto_increment,
description varchar(200) not null,
registration_date date
);

create table if not exists client (
id bigint primary key auto_increment,
name varchar(200) not null,
address longtext, 
phone varchar(30),
registration_date date
);

create table if not exists service(
id bigint primary key auto_increment,
description varchar(200) not null,
price decimal(10,2),
registration_date date
);

create table if not exists product(
id bigint primary key auto_increment,
description varchar(200) not null,
brand_product varchar(200), 
warranty varchar(100),
price decimal(10,2),
registration_date date
);

create table if not exists service_order(
id bigint primary key auto_increment,
id_client bigint not null,
id_service bigint,
id_payment bigint not null,
quantity numeric(10,2) not null, 
total decimal (10,2) not null,
foreign key (id_client) references client(id),
foreign key (id_service) references service(id),
foreign key (id_payment) references payment(id)
);

create table if not exists service_order_product(
id bigint primary key auto_increment,
service_order_id bigint not null,
product_id bigint not null,
foreign key (service_order_id) references service_order(id),
foreign key (product_id) references product(id)
)







