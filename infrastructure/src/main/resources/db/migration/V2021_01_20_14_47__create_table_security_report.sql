CREATE TABLE `security_report` (
    `id` bigint NOT NULL COMMENT '报告的索引，一次扫描产生一个索引',
    `userId` bigint DEFAULT NULL COMMENT '发起扫描的用户id',
    `path` varchar(200) DEFAULT NULL COMMENT '报告所在的路径',
    `createTime` timestamp NULL DEFAULT NULL COMMENT '报告的创建时间',
    `createBy` varchar(200) DEFAULT NULL COMMENT '报告的创建用户名',
    PRIMARY KEY (`id`)
);
