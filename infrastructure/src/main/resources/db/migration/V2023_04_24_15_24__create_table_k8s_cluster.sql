create table `k8s_cluster`
(
    `id`          bigint auto_increment primary key comment '主键',
    `project_id`          bigint,
    `name`        varchar(128)                        not null,
    `kubeconfig_content`    text,
    `connected`   tinyint,
    created_at    timestamp default CURRENT_TIMESTAMP not null,
    updated_at    timestamp default CURRENT_TIMESTAMP not null on update CURRENT_TIMESTAMP
);
