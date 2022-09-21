CREATE TABLE `security_scan_result` (
    `id` int not null primary key auto_increment,
    `usecase_id` int DEFAULT NULL COMMENT '用例的id，是外键，关联security_usecase的id',
    `result` int DEFAULT NULL COMMENT '用例扫描结果，0表示扫描中，1表示失败，2表示成功；',
    `create_by` varchar(50) DEFAULT NULL,
    `create_time` timestamp NULL DEFAULT NULL,
    `update_by` varchar(50) DEFAULT NULL,
    `update_time` timestamp NULL DEFAULT NULL
);
