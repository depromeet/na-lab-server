create table if not exists gallery (
    gallery_id bigint primary key,
    target_id BIGINT unique not null,
    survey_id BIGINT unique not null,
    created_at TIMESTAMP(6) not null,
    updated_at TIMESTAMP(6) not null
);
