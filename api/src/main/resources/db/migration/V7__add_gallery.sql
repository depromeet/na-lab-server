create table if not exists gallery (
    gallery_id BIGINT primary key,
    target_id BIGINT unique not null,
    survey_id BIGINT unique not null,
    bookmarked_count INT not null,
    job TEXT not null,
    update_order TIMESTAMP(6) not null,
    created_at TIMESTAMP(6) not null,
    updated_at TIMESTAMP(6) not null
);
