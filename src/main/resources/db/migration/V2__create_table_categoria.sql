create table categoria(
    id serial not null,
    titulo varchar(80) not null,
    cor varchar(20) not null,
    primary key(id)
);

alter table video add column id_categoria bigint;
alter table video add foreign key(id_categoria) references categoria(id);