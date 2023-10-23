use golajuma;
create table user
(
    user_id       bigint auto_increment
        primary key,
    created_date  datetime(6)  not null,
    deleted       bit          not null,
    updated_date  datetime(6)  not null,
    user_email    varchar(255) not null,
    user_nickname varchar(255) not null,
    user_password varchar(255) not null
);

create table auth_info
(
    auth_info_id    bigint auto_increment
        primary key,
    created_date    datetime(6)  not null,
    deleted         bit          not null,
    updated_date    datetime(6)  not null,
    auth_info_token varchar(255) not null,
    auth_info_type  varchar(255) not null,
    user_id         bigint       not null
);


create table vote
(
    vote_id          bigint auto_increment
        primary key,
    created_date     datetime(6)   not null,
    deleted          bit           not null,
    updated_date     datetime(6)   not null,
    vote_category    varchar(255)  not null,
    user_id          bigint        not null,
    vote_content     varchar(1000) null,
    vote_end_date    datetime(6)   not null,
    vote_title       varchar(256)  not null,
    vote_total_count bigint        not null,
    vote_type        varchar(255)  null
);

create table decision
(
    decision_id        bigint auto_increment
        primary key,
    created_date       datetime(6) not null,
    deleted            bit         not null,
    updated_date       datetime(6) not null,
    decision_option_id bigint      not null,
    decision_user_id   bigint      not null
);

create table comment
(
    comment_id      bigint auto_increment
        primary key,
    created_date    datetime(6)  not null,
    deleted         bit          not null,
    updated_date    datetime(6)  not null,
    comment_content varchar(255) not null,
    comment_user_id bigint       not null,
    comment_vote_id bigint       not null
);

create table vote_option
(
     option_id bigint auto_increment
         primary key,
     created_date datetime(6) not null,
     deleted bit not null,
     updated_date datetime(6) not null,
     option_count bigint,
     option_image varchar(255),
     option_name varchar(255) not null,
     option_vote_id bigint not null
);