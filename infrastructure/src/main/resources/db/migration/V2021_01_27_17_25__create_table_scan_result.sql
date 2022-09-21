create table `scan_result`
(
    `id`           int          not null primary key auto_increment,
    `scan_task_id` int          not null,
    `result_path`  varchar(256) not null,
    `result` int,
    `use_case_id` int,
    `created_at` timestamp default current_timestamp,
    `updated_at` timestamp default current_timestamp
);
