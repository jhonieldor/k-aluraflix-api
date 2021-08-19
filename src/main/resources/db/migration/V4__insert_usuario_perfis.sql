insert into perfil(nome) values
    ('ROLE_ADMIN'),
    ('ROLE_USER');

insert into usuario(nome, email, senha) values
    ('Admin', 'admin@alura.com.br', '$2a$10$KeUqTQxRhy1t2EgfBzouWu9UY3J77svuNJozRCuj2WvbjratvX6zu'),
    ('Usuario', 'user@alura.com.br', '$2a$10$Uy94Z/bdJTJKY/Wg1xjQp.A19jPxyJ8wLZ9ih52rTMYVIoHkq/7p.');

insert into usuario_perfis(usuario_id, perfis_id) values
    ((select id from usuario where nome = 'Admin'), (select id from perfil where nome = 'ROLE_ADMIN')),
    ((select id from usuario where nome = 'Usuario'), (select id from perfil where nome = 'ROLE_USER'));