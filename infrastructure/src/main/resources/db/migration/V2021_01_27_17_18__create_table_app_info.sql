create table `app_info`
(
    `id`          bigint auto_increment primary key comment '主键',
    `project_id`  bigint                                not null,
    `name`        varchar(128)                          not null,
    `description` varchar(512),
    `repo`        varchar(512),
    `branch`      varchar(64) default 'master'          not null,
    `username`    varchar(128),
    `password`    varchar(256),
    `repo_type`   varchar(64)                           not null,
    `code_path`   varchar(256),
    `workdir`     varchar(512),
    created_at    timestamp   default CURRENT_TIMESTAMP not null,
    updated_at    timestamp   default CURRENT_TIMESTAMP not null on update CURRENT_TIMESTAMP
);
