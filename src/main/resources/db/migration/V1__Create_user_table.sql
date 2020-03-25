create table user
(
    id           BIGINT auto_increment,
    account_id   VARCHAR(100),
    name         VARCHAR(50),
    token        CHAR(36),
    gmt_create   BIGINT,
    gmt_modified BIGINT,
    bio          VARCHAR(256),
    avatar_url   VARCHAR(100),
    constraint user_PK
        primary key (ID)
);
create table question
(
    id BIGINT auto_increment,
    title VARCHAR(50),
    description text,
    gmt_create BIGINT,
    gmt_modified BIGINT,
    creator BIGINT not null,
    comment_count INTEGER default 0,
    view_count INTEGER default 0,
    like_count INTEGER default 0,
    tag VARCHAR(256),
    constraint question_PK
        primary key (ID)
);
create table comment
(
    id BIGINT auto_increment,
    parent_id BIGINT not null,
    type INTEGER not null,
    commentator BIGINT not null,
    gmt_create BIGINT not null,
    gmt_modified BIGINT not null,
    like_count BIGINT default 0,
    content VARCHAR(1024),
    comment_count INTEGER default 0,
    constraint comment_PK
        primary key (ID)
);
create table notification
(
    id BIGINT auto_increment,
    notifier BIGINT not null,
    receiver BIGINT not null,
    outerid BIGINT not null,
    type int not null,
    gmt_create BIGINT not null,
    status int default 0 not null,
    notifier_name varchar(100),
    outer_title varchar(256),
    constraint notification_PK
        primary key (ID)
);
create table ad(
    id BIGINT auto_increment,
    title varchar(256),
    url varchar(512),
    image varchar(256),
    gmt_star   BIGINT,
    gmt_end BIGINT,
    gmt_create   BIGINT,
    gmt_modified BIGINT,
    status int,
    pos varchar(10),
    constraint ad_PK
        primary key (ID)
);