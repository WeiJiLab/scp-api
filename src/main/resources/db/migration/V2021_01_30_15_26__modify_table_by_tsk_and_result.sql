-- alter table `scan_task` change `engine_id` 'tool_id' int not null;


alter table `scan_task`  drop  `engine_id`;

alter table `scan_task`  add column tool_id int;

-- auto-generated definition
drop table if exists `scan_result`;
create table `scan_result`
(
    id           int auto_increment primary key,
    scan_task_id int                                 not null,
    result_path  varchar(256)                        not null,
    result       int                                 null,
    created_at   timestamp default CURRENT_TIMESTAMP not null,
    updated_at   timestamp default CURRENT_TIMESTAMP not null
);
