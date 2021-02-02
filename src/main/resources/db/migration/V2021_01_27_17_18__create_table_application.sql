create table `application`
(
    `id`          int         not null primary key auto_increment,
    `project_id`  int,
    `name`        varchar(64) not null,
    `description`        varchar(256),
    `owner_id`    int,
    `created_at` timestamp default current_timestamp,
    `updated_at` timestamp default current_timestamp
);
