create table video(
    id serial not null,
    titulo varchar(80) not null,
    descricao varchar(200) not null,
    url text,
    primary key(id)
)

