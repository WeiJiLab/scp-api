create table `user_roles`
(
    user_id bigint not null,
    role_id bigint not null,
    primary key (user_id, role_id),
    constraint FK55itppkw3i07do3h7qoclqd4k foreign key (user_id) references `user` (id),
    constraint FKh8ciramu9cc9q3qcqiv4ue8a6 foreign key (role_id) references `roles` (id)
);

