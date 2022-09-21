create table roles
(
    id     bigint auto_increment primary key,
    `role` varchar(255) null,
    constraint uk_roles_role unique (`role`)
);


# 初始化 roles

INSERT INTO roles (id, `role`) VALUES (1, 'ROLE_USER');
INSERT INTO roles (id, `role`) VALUES (2, 'ROLE_MODERATOR');
INSERT INTO roles (id, `role`) VALUES (3, 'ROLE_ADMIN');

