create table AppUser (
	userid identity,
	first_name varchar(30) not null,
	last_name varchar(30) not null,
	email varchar(30) not null
);

create table Customer (
	id identity,
	address1 varchar(30),
	city varchar(30),
	state varchar(2),
	zip_code varchar(5)
);