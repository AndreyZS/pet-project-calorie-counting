create table Account
(
    id    bigserial primary key,
    login varchar(255) unique not null,
    heft  double precision    not null,
    dob   date                not null
)