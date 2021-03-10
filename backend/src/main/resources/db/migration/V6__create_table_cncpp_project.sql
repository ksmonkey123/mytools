create table cncpp_project (
    id serial,
    user_id bigint(20) unsigned not null,
    name varchar(40) not null,
    constraint `cncpp_project_fk_user_id_user` foreign key (user_id) references user (id)
);
