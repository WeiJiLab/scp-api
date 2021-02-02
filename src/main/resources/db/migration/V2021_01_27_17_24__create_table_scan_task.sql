create table `scan_task`
(
    `id`          int not null primary key auto_increment,
    `app_id`      int not null,
    `engine_id`   int not null,
    `use_case_id` int not null,
    `status`      varchar(32) default 'READY',
    `start_time`  timestamp   default current_timestamp,
    `end_time`    timestamp   default current_timestamp,
    `created_at` timestamp default current_timestamp,
    `updated_at` timestamp default current_timestamp
);
