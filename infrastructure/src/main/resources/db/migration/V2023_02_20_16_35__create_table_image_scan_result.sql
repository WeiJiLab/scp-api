create table `image_scan_result`
(
    id                bigint auto_increment PRIMARY KEY comment '主键',
    pj_id             varchar(128)  NOT NULL comment '项目id',
    type_option       int           NOT NULL comment '项目类型',
    sdlc              varchar(128)  NOT NULL comment 'sdlc',
    res_count           int           NOT NULL comment 'count',
    detail_status       int           NOT NULL comment '0, 1',
    result            varchar(1024) NOT NULL comment 'result',
    FOREIGN KEY (pj_id) REFERENCES image_scan_job (pj_id)
);

