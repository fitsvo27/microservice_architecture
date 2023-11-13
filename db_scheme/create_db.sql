CREATE ROLE study WITH
  NOLOGIN
  NOSUPERUSER
  INHERIT
  CREATEDB
  NOCREATEROLE
  NOREPLICATION;

DROP DATABASE IF EXISTS users;

CREATE DATABASE users
    WITH
    OWNER = study
    ENCODING = 'UTF8'
    LC_COLLATE = 'en_US.utf8'
    LC_CTYPE = 'en_US.utf8'
    LOCALE_PROVIDER = 'libc'
    TABLESPACE = pg_default
    CONNECTION LIMIT = -1
    IS_TEMPLATE = False;

CREATE SCHEMA IF NOT EXISTS users_scheme
    AUTHORIZATION study;


CREATE TABLE IF NOT EXISTS users_scheme."Users"
(
    id integer NOT NULL,
    login character varying(255) COLLATE pg_catalog."default" NOT NULL,
    lastname character varying(255) COLLATE pg_catalog."default" NOT NULL,
    firstname character varying(255) COLLATE pg_catalog."default" NOT NULL,
    middlename character varying(255) COLLATE pg_catalog."default",
    gender character(1) COLLATE pg_catalog."default" NOT NULL,
    date_of_birth date,
    avatar_link character varying COLLATE pg_catalog."default",
    email character varying COLLATE pg_catalog."default",
    phone_number character varying COLLATE pg_catalog."default" NOT NULL,
    deleted bit(1),
    CONSTRAINT "Users_pkey" PRIMARY KEY (id),
    CONSTRAINT avatar_link_un UNIQUE (avatar_link),
    CONSTRAINT email_un UNIQUE (email),
    CONSTRAINT login UNIQUE (login),
    CONSTRAINT phone_number_un UNIQUE (phone_number),
    CONSTRAINT users_image_fkey FOREIGN KEY (id)
        REFERENCES users_scheme.images_storage (user_id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
        NOT VALID
)

TABLESPACE pg_default;

ALTER TABLE IF EXISTS users_scheme."Users"
    OWNER to study;


CREATE TABLE IF NOT EXISTS users_scheme.friends
(
    user_id bigint NOT NULL,
    user_friend_id bigint NOT NULL,
    CONSTRAINT users_fkey FOREIGN KEY (user_id)
        REFERENCES users_scheme."Users" (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
        NOT VALID
)

TABLESPACE pg_default;

ALTER TABLE IF EXISTS users_scheme.friends
    OWNER to study;

CREATE TABLE IF NOT EXISTS users_scheme.images_storage
(
    user_id bigint NOT NULL,
    s3_link bit varying(255) NOT NULL,
    CONSTRAINT users_pkey PRIMARY KEY (user_id)
        INCLUDE(user_id),
    CONSTRAINT users_fkey FOREIGN KEY (user_id)
        REFERENCES users_scheme."Users" (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
        NOT VALID
)

TABLESPACE pg_default;

ALTER TABLE IF EXISTS users_scheme.images_storage
    OWNER to study;

CREATE INDEX IF NOT EXISTS fki_users_fkey
    ON users_scheme.images_storage USING btree
    (user_id ASC NULLS LAST)
    TABLESPACE pg_default;