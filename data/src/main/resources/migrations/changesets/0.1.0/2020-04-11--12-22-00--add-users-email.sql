alter table users add email varchar(64) null;

create unique index users_email_uindex on users (email);
