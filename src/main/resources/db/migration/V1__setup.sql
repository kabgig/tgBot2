CREATE TABLE ACTIVE_CHAT (
    id serial primary key not null,
    chat_id integer not null
    );
CREATE TABLE active_chat (
    id serial primary key not null,
    chat_id integer not null
    );
create table incomes (
  id serial primary key not null,
  chat_id integer not null,
  income numeric
);

create table spend (
  id serial primary key not null,
  chat_id integer not null,
  spend numeric
);