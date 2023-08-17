create table db_scholarship.class (
	id bigint not null primary key auto_increment,
    name varchar(250) not null,
    status varchar (250) not null,
    duration varchar (250) not null
);

create table db_scholarship.organizer (
	id bigint not null primary key auto_increment,
    name varchar(250) not null,
    type varchar (250) not null    
);

create table db_scholarship.organizer_class (
	id_organizer bigint not null,
    id_class bigint not null, 
    primary key (id_organizer, id_class),
    foreign key (id_organizer) references organizer (id),
	foreign key (id_class) references class (id)
);

create table db_scholarship.squad (
	id bigint not null primary key auto_increment,
    id_class bigint not null,
    name varchar (250) not null,
    foreign key (id_class) references class (id)
);

create table db_scholarship.student (
	id bigint not null primary key auto_increment,
    id_class bigint not null,
    id_squad bigint,
	name varchar (250) not null,
    foreign key (id_squad) references squad (id),
    foreign key (id_class) references class (id)
);

insert into db_scholarship.class(name, status, duration) values ('SB_Journey','WAITING','5 months');

insert into db_scholarship.student(id_class, name) values (1, 'robson');
insert into db_scholarship.student(id_class, name) values (1, 'duds');
insert into db_scholarship.student(id_class, name) values (1, 'yuri');
insert into db_scholarship.student(id_class, name) values (1, 'theo');
insert into db_scholarship.student(id_class, name) values (1, 'eduardo');
insert into db_scholarship.student(id_class, name) values (1, 'fabio');
insert into db_scholarship.student(id_class, name) values (1, 'joao');
insert into db_scholarship.student(id_class, name) values (1, 'henrique');
insert into db_scholarship.student(id_class, name) values (1, 'pedro');
insert into db_scholarship.student(id_class, name) values (1, 'augusto');
insert into db_scholarship.student(id_class, name) values (1, 'daniel');
insert into db_scholarship.student(id_class, name) values (1, 'tony');
insert into db_scholarship.student(id_class, name) values (1, 'jose');
insert into db_scholarship.student(id_class, name) values (1, 'ademir');

insert into db_scholarship.organizer(name, type) values ('edmar', 'INSTRUCTOR');
insert into db_scholarship.organizer(name, type) values ('giovanni', 'INSTRUCTOR');
insert into db_scholarship.organizer(name, type) values ('maxi', 'INSTRUCTOR');

insert into db_scholarship.organizer(name, type) values ('yago', 'SCRUM_MASTER');
insert into db_scholarship.organizer(name, type) values ('liliv', 'SCRUM_MASTER');
insert into db_scholarship.organizer(name, type) values ('clara', 'SCRUM_MASTER');

insert into db_scholarship.organizer(name, type) values ('leticia', 'COORDINATOR');
insert into db_scholarship.organizer(name, type) values ('julia', 'COORDINATOR');
insert into db_scholarship.organizer(name, type) values ('carlos', 'COORDINATOR');

insert into db_scholarship.squad (id_class, name) values (1, 'andorinhas');
insert into db_scholarship.squad (id_class, name) values (1, 'beija-flor');
insert into db_scholarship.squad (id_class, name) values (1, 'papagaio');
insert into db_scholarship.squad (id_class, name) values (1, 'gaivota');