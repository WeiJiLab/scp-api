create table `image_scan_report`
(
    id                bigint auto_increment PRIMARY KEY comment '主键',
    pj_id             bigint DEFAULT 123456 comment '项目id',
    file_name          varchar(128),
    scan_report_url        varchar(128),
    create_at         TIMESTAMP DEFAULT CURRENT_TIMESTAMP comment '创建时间',
    FOREIGN KEY (pj_id) REFERENCES image_scan_job (pj_id)
);
