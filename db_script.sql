create table roles
(
    role_id     serial primary key,
    code        varchar(64) unique not null,
    description varchar(1024)
);

create table users
(
    user_id  bigserial primary key,
    email    varchar(256) unique not null,
    login    varchar(256) unique not null,
    password varchar(256)        not null,
    role_id  integer references roles (role_id)
);

create table email_verification_token
(
    token_id  bigserial primary key,
    token    varchar(512) unique not null,
    user_id  bigint references users (user_id),
    expiry_date timestamp
);

alter table users add column enabled boolean default false;



