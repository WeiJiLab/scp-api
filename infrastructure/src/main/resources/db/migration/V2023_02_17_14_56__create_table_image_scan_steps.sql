create table `image_scan_steps`
(
    id                bigint auto_increment PRIMARY KEY comment '主键',
    pj_id             varchar(128)  NOT NULL comment '项目id',
    type_option       int           NOT NULL comment '项目类型',
    status            int           NOT NULL comment 'step status',
    step              int           NOT NULL comment 'current step',
    stage             varchar(128)  NOT NULL comment 'define stage',
    logs              varchar(1024) DEFAULT NULL comment 'log',
    FOREIGN KEY (pj_id) REFERENCES image_scan_job (pj_id)
);
