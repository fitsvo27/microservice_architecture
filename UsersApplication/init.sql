CREATE SCHEMA IF NOT EXISTS users_scheme
    AUTHORIZATION postgres;

GRANT ALL ON SCHEMA users_scheme TO postgres;
GRANT USAGE ON SCHEMA users_scheme TO postgres;

CREATE TABLE IF NOT EXISTS users_scheme.users
(
    id integer NOT NULL GENERATED ALWAYS AS IDENTITY ( INCREMENT 1 START 1 MINVALUE 1 MAXVALUE 2147483647 CACHE 1 ),
    login character varying(255) COLLATE pg_catalog."default" NOT NULL,
    lastname character varying(255) COLLATE pg_catalog."default" NOT NULL,
    firstname character varying(255) COLLATE pg_catalog."default" NOT NULL,
    middlename character varying(255) COLLATE pg_catalog."default",
    gender character(1) COLLATE pg_catalog."default" NOT NULL,
    date_of_birth timestamp,
    avatar_link character varying COLLATE pg_catalog."default",
    email character varying COLLATE pg_catalog."default",
    phone_number character varying COLLATE pg_catalog."default" NOT NULL,
    deleted boolean,
    CONSTRAINT "Users_pkey" PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS users_scheme.friends
(
    user_id bigint NOT NULL,
    user_friend_id bigint NOT NULL
);

CREATE TABLE IF NOT EXISTS users_scheme.images_storage
(
    user_id bigint NOT NULL,
    s3_link bit varying(255) NOT NULL
);
