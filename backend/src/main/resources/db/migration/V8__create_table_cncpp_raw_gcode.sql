create table cncpp_raw_gcode (
    id serial,
    project_id bigint unsigned not null,
    name varchar(255) not null,
    datastore_entry_id bigint unsigned not null,
    constraint `cncpp_raw_gcode_fk_project_id_cncpp_project` foreign key (project_id) references cncpp_project (id),
    constraint `cncpp_raw_gcode_fk_datastore_entry_id_datastore_entry` foreign key (datastore_entry_id) references datastore_entry (id) on delete cascade
);
