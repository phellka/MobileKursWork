create table tests
(
    id         bigserial
        constraint tests_pk
            primary key,
    name       varchar(255)            not null,
    created_at timestamp default now() not null,
    updated_at timestamp
);

alter table tests
    owner to postgres;


create table questions
(
    id         bigserial
        constraint questions_pk
            primary key,
    text       varchar(255)            not null,
    created_at timestamp default now() not null,
    updated_at timestamp,
    test_id    bigint                  not null
        constraint questions_tests_id_fk
            references tests
);

alter table questions
    owner to postgres;

-- auto-generated definition
create table answers
(
    id          bigserial
        constraint answers_pk
            primary key,
    answer_text varchar(255)            not null,
    is_correct  boolean   default false not null,
    created_at  timestamp default now() not null,
    updated_at  timestamp,
    question_id bigint                  not null
        constraint answers_questions_id_fk
            references questions
);

alter table answers
    owner to postgres;



