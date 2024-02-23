alter table target add `job` TEXT;
alter table target add image_url TEXT;
alter table target add `version` BIGINT;

create table if not exists bookmarked_survey (
  target_id BIGINT not null,
  bookmarked_survey_id BIGINT not null,
  foreign key (target_id) references target (target_id),
  foreign key (bookmarked_survey_id) references survey (survey_id)
);
