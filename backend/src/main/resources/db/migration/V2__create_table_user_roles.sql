create table user_roles (
    id serial,
    user_id bigint(20) unsigned not null,
    role varchar(20) not null,
    constraint `user_roles_fk_user_id_user` foreign key (user_id) references user (id)
);

insert into user_roles (user_id, role) select id, 'USER' from user;
