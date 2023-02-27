create table `image_scan_result`
(
    id                bigint auto_increment PRIMARY KEY comment '主键',
    pj_id             bigint DEFAULT 123456 comment '项目id',
    type_option       int           DEFAULT 0 NOT NULL comment '项目类型',
    sdlc              varchar(128)  DEFAULT 'check' NOT NULL comment 'sdlc',
    res_count           int           DEFAULT 0 NOT NULL comment 'count',
    detail_status       int           DEFAULT 0 NOT NULL comment '0, 1',
    result            MEDIUMTEXT NOT NULL comment 'result',
    time_stamp         TIMESTAMP DEFAULT CURRENT_TIMESTAMP comment '创建时间',
    FOREIGN KEY (pj_id) REFERENCES image_scan_job (pj_id)
);

