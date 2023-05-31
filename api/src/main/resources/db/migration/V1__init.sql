create table target (
    target_id bigint not null,
    created_at TIMESTAMP(6) not null,
    updated_at TIMESTAMP(6) not null,
    target_name varchar(255) not null,
    primary key (target_id)
);

create table reviewer (
    reviewer_id bigint not null,
    created_at TIMESTAMP(6) not null,
    updated_at TIMESTAMP(6) not null,
    collaboration_experience boolean not null,
    nick_name varchar(255) not null,
    position varchar(255) not null,
    primary key (reviewer_id)
);

create table survey (
    survey_id bigint not null,
    created_at TIMESTAMP(6) not null,
    updated_at TIMESTAMP(6) not null,
    target_id bigint not null,
    primary key (survey_id),
    foreign key (target_id) references target (target_id)
);

create table form_question (
    dtype varchar(31) not null,
    form_question_id bigint not null,
    created_at TIMESTAMP(6) not null,
    updated_at TIMESTAMP(6) not null,
    orders integer not null,
    question_type varchar(255),
    title varchar(45) not null,
    short_form_question_type varchar(255),
    choice_question_type varchar(255),
    max_selection_count integer,
    survey_id bigint not null,
    primary key (form_question_id),
    foreign key (survey_id) references survey (survey_id)
);

create table feedback (
    feedback_id bigint not null,
    created_at TIMESTAMP(6) not null,
    updated_at TIMESTAMP(6) not null,
    is_read boolean not null,
    survey_id bigint not null,
    reviewer_id bigint not null,
    primary key (feedback_id),
    foreign key (reviewer_id) references reviewer (reviewer_id),
    foreign key (survey_id) references survey (survey_id)
);

create table form_feedback (
    dtype varchar(31) not null,
    form_feedback_id bigint not null,
    is_read boolean,
    form_question_id bigint not null,
    feedback_id bigint not null,
    primary key (form_feedback_id),
    foreign key (form_question_id) references form_question (form_question_id),
    foreign key (feedback_id) references feedback (feedback_id)
);

create table choice (
    choice_id bigint not null,
    content varchar(18) not null,
    orders integer not null,
    form_question_id bigint not null,
    primary key (choice_id),
    foreign key (form_question_id) references form_question (form_question_id)
);

create table reply (
    form_feedback_id bigint not null,
    replies varchar(255),
    foreign key (form_feedback_id) references form_feedback (form_feedback_id)
);

create table selects (
    form_feedback_id bigint not null,
    selects bigint,
    foreign key (form_feedback_id) references form_feedback (form_feedback_id)
);
