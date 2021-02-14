create table user (
    id serial,
    username varchar(20) not null,
    password varchar(60) not null,
    UNIQUE KEY (username)
);
