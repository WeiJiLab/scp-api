create table `image_scan_job`
(
    pj_id             bigint    DEFAULT 123456 PRIMARY KEY comment '项目id',
    pj_name           varchar(128)  DEFAULT NULL comment '项目名称',
    type_option       int           DEFAULT 0 not null comment '项目类型',
    create_time         TIMESTAMP DEFAULT CURRENT_TIMESTAMP comment '创建时间'
);
