create table users (
    id bigint not null,
    created_at timestamp(6) not null,
    updated_at timestamp(6) not null,
    nickname varchar(255) not null,
    email varchar(255) not null,
    primary key (id)
);

create table user_oauth_info (
    id bigint not null,
    provider varchar(255) not null,
    email varchar(255) not null,
    username varchar(255),
    phone_number varchar(255),
    user_entity_id bigint,
    created_at timestamp(6),
    updated_at timestamp(6),
    primary key (id),
    foreign key (user_entity_id) references users (id)
);
