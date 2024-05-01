create table "measurements"
(
    "id"            bigint                      not null,
    "list_type"     "text"                      not null,
    "operation"     "text"                      not null,
    "first"         bigint                      not null,
    "middle"        bigint                      not null,
    "last"          bigint                      not null,
    "imported_at"   timestamp with time zone    not null,
    primary key ("id")
);
