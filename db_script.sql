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

