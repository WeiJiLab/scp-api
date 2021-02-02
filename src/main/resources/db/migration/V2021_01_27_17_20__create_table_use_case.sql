create table `use_case`
(
    `id`          int          not null primary key auto_increment,
    `name`        varchar(64)  not null,
    `description`        varchar(256),
    `security_tool_id`   int          not null,
    `script_path` varchar(256) not null,
    `created_at` timestamp default current_timestamp,
    `updated_at` timestamp default current_timestamp
);
