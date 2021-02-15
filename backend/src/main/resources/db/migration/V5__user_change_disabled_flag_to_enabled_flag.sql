alter table user add column (enabled boolean not null);
update user set enabled = true where !disabled;
alter table user drop column disabled;