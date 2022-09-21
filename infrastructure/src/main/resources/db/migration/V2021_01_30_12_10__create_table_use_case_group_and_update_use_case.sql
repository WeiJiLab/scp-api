create table `use_case_group`
(
    `id`          int                                 not null primary key auto_increment,
    `name`        varchar(64)                         not null,
    `description` varchar(256),
    `tool_id`     int                                 not null,
    `created_at`  timestamp default current_timestamp not null,
    `updated_at`  timestamp default current_timestamp not null on update current_timestamp
);


alter table `use_case`
    add column use_case_group_id int;