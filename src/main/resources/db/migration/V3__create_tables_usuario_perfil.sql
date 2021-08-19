create table perfil(
    id serial not null,
    nome varchar(20) not null,
    primary key(id)
);

create table usuario(
    id serial not null,
    nome varchar(80) not null,
    email varchar(80) not null,
    senha varchar(80) not null,
    primary key(id)
);

create table usuario_perfis(
    usuario_id bigint not null,
    perfis_id bigint not null,
    primary key(usuario_id, perfis_id),
    foreign key(usuario_id) references usuario(id),
    foreign key(perfis_id) references perfil(id)
);