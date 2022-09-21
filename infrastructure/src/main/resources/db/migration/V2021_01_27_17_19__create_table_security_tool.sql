create table `security_tool`
(
    `id`          int         not null primary key auto_increment,
    `name`        varchar(64) not null,
    `category`    varchar(64),
    `description`        varchar(256),
    `metadata`    varchar(256),
    `created_at` timestamp default current_timestamp,
    `updated_at` timestamp default current_timestamp
);
