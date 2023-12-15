CREATE TABLE IF NOT EXISTS friends
(
    user_id bigint NOT NULL,
    user_friend_id bigint NOT NULL,
    CONSTRAINT user_friends_pk PRIMARY KEY (user_id,user_friend_id),
    CONSTRAINT fk_user_friend FOREIGN KEY (user_id)
        REFERENCES public.users (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
);
