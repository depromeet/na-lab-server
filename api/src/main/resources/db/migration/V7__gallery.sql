create table if not exists gallery(
       id bigint not null,
       created_at TIMESTAMP(6) not null,
       updated_at TIMESTAMP(6) not null,
       feedback_reply varchar(255),
       feedback_count integer not null,
       survey_id bigint not null unique,
       save_count integer not null,
       user_id bigint not null unique,
       user_name varchar(255) not null,
       user_nickname varchar(255) not null,
       user_image_url varchar(255),
       version bigint,
       primary key (id)
);

create index gallery_idx_user_id on gallery(user_id);

create table gallery_survey_tendency(
       id bigint not null,
       tendency_name varchar(255),
       tendency_count integer
);

alter table gallery_survey_tendency
add constraint gallery_fk_gallery_survey_tendency
foreign key (id) references gallery(id);

