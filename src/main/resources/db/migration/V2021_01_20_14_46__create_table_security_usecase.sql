CREATE TABLE `security_usecase` (
    `id` int not null primary key auto_increment,
    `description` varchar(200) DEFAULT NULL,
    `url` varchar(300) DEFAULT NULL,
    `name` varchar(80) DEFAULT NULL,
    `create_by` varchar(300) DEFAULT NULL,
    `create_time` timestamp NULL DEFAULT NULL,
    `update_by` varchar(300) DEFAULT NULL,
    `update_time` timestamp NULL DEFAULT NULL
);
