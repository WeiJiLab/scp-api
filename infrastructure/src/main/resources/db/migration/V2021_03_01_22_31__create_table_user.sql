create table `user`
(
    id                bigint auto_increment primary key comment '主键',
    username          varchar(128)                        not null comment '用户名',
    email             varchar(255)                        null comment '邮箱',
    `password`        varchar(255)                        not null comment '密码',
    avatar            varchar(512)                        null comment '头像',
    is_active         bit       default 1                 not null comment '是否为激活',
    is_email_verified bit       default 0                 not null comment '是否认证邮箱',
    created_at        timestamp default CURRENT_TIMESTAMP not null,
    updated_at        timestamp default CURRENT_TIMESTAMP not null on update CURRENT_TIMESTAMP,
    constraint uk_user_email
        unique (email),
    constraint uk_user_username
        unique (username)
);
