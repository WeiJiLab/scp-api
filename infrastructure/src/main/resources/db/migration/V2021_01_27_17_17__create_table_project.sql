create table `project`
(
    `id`          bigint auto_increment primary key comment '主键',
    `name`        varchar(128)                        not null,
    `description` varchar(512),
    `owner_id`    bigint,
    created_at    timestamp default CURRENT_TIMESTAMP not null,
    updated_at    timestamp default CURRENT_TIMESTAMP not null on update CURRENT_TIMESTAMP
);
