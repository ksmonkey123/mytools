create table user (
    id serial,
    username varchar(20) not null,
    password varchar(60) not null,
    UNIQUE KEY (username)
);

insert into user_roles (user_id, role) select id, 'USER' from user;

select * from user_roles;

commit;