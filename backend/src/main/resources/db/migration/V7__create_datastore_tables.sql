create table datastore_entry_data (
    id serial,
    data longblob
);

create table datastore_entry (
    id serial,
    user_id bigint(20) unsigned not null,
    category varchar(255) not null,
    title varchar(255) not null,
    content_id bigint(20) unsigned not null,
    constraint `datastore_entry_fk_user_id_user` foreign key (user_id) references user (id),
    constraint `datastore_entry_fk_content_id_datastore_entry_data` foreign key (content_id) references datastore_entry_data (id) on delete cascade
);
