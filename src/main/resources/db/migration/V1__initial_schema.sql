CREATE TABLE IF NOT EXISTS "measurements"
(
    "id"            bigint                      not null,
    "list_type"     "text"                      not null,
    "method"        "text"                      not null,
    "start"         bigint                      not null,
    "middle"        bigint                      not null,
    "ending"        bigint                      not null,
    "timestamp"   timestamp with time zone      not null,
    primary key ("id")
);
