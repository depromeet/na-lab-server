ALTER TABLE form_feedback ADD is_bookmarked boolean not null default false;
ALTER TABLE form_feedback ADD bookmarked_at timestamp(6);
