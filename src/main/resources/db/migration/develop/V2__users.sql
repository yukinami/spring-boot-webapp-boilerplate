create table authorities (
  username varchar_ignorecase(50) not null,
  authority varchar_ignorecase(50) not null
);
create unique index ix_auth_username on authorities (username,authority);
