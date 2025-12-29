create table stock (
num number(5) not null,
value number(5) default 0 not null,
constraint STOCK_PK primary key(num) );

insert into stock values (1, 220);
insert into stock values (2, 260);
insert into stock values (3, 290);
insert into stock values (4, 340);
insert into stock values (5, 390);
insert into stock values (6, 340);
insert into stock values (7, 280);
insert into stock values (8, 270);
insert into stock values (9, 190);
insert into stock values (10, 230);

select * from stock;