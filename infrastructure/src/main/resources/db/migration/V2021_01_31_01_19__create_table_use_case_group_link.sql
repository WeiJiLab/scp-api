create table scp.use_case_group_link
(
    `id`                int                                 not null primary key auto_increment,
    `use_case_id`       int                                 not null,
    `use_case_group_id` int                                 not null,
    `created_at`        timestamp default current_timestamp not null,
    `updated_at`        timestamp default current_timestamp not null on update current_timestamp
);