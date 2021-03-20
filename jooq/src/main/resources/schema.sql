CREATE TABLE IF NOT EXISTS users (
    user_id identity,
    username varchar2(64) NOT NULL,
    password varchar2(64) NOT NULL,
    email varchar2(128) NOT NULL,
    bio varchar2(256),
    image varchar2(256),
    CONSTRAINT users_pkey PRIMARY KEY (user_id),
    CONSTRAINT users_email_key UNIQUE (email),
    CONSTRAINT users_username_key UNIQUE (username)
);

CREATE TABLE IF NOT EXISTS articles (
    article_id identity,
    user_id integer NOT NULL,
    slug varchar2(256) ,
    title varchar2(256) ,
    description varchar2(256) ,
    body clob ,
    created_at timestamp NOT NULL,
    updated_at timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT articles_pkey PRIMARY KEY (article_id),
    CONSTRAINT articles_slug_key UNIQUE (slug),
    CONSTRAINT articles_user_id_fkey FOREIGN KEY (user_id)
        REFERENCES users (user_id)
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
);

CREATE TABLE IF NOT EXISTS article_favorites (
    id identity,
    article_id integer NOT NULL,
    user_id integer NOT NULL,
    CONSTRAINT article_favorites_pkey PRIMARY KEY (id),
    CONSTRAINT article_favorites_article_id_fkey FOREIGN KEY (article_id)
        REFERENCES articles (article_id)
        ON UPDATE NO ACTION
        ON DELETE NO ACTION,
    CONSTRAINT article_favorites_user_id_fkey FOREIGN KEY (user_id)
        REFERENCES users (user_id)
        ON UPDATE NO ACTION
        ON DELETE NO ACTION,
    CONSTRAINT article_favorites_unique UNIQUE(article_id, user_id)
);

CREATE TABLE IF NOT EXISTS user_follows (
    id identity,
    user_id integer NOT NULL,
    fan_id integer NOT NULL,
    CONSTRAINT user_follows_pkey PRIMARY KEY (id),
    CONSTRAINT user_follows_fan_id_fkey FOREIGN KEY (fan_id)
        REFERENCES users (user_id)
        ON UPDATE NO ACTION
        ON DELETE NO ACTION,
    CONSTRAINT user_follows_user_id_fkey FOREIGN KEY (user_id)
        REFERENCES users (user_id)
        ON UPDATE NO ACTION
        ON DELETE NO ACTION,
    CONSTRAINT user_follows_unique UNIQUE(user_id, fan_id)
);

CREATE TABLE IF NOT EXISTS tags (
    tag_id identity,
    name varchar(256),
    CONSTRAINT tags_pkey PRIMARY KEY (tag_id)
);

CREATE TABLE IF NOT EXISTS article_tags (
    id identity,
    article_id integer NOT NULL,
    tag_id integer NOT NULL,
    CONSTRAINT article_tags_pkey PRIMARY KEY (id),
    CONSTRAINT article_tags_article_id_fkey FOREIGN KEY (article_id)
        REFERENCES articles (article_id)
        ON UPDATE NO ACTION
        ON DELETE NO ACTION,
    CONSTRAINT article_tags_tag_id_fkey FOREIGN KEY (tag_id)
        REFERENCES tags (tag_id)
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
);

CREATE TABLE IF NOT EXISTS comments (
    comment_id identity,
    body text,
    article_id integer NOT NULL,
    user_id integer NOT NULL,
    created_at timestamp NOT NULL,
    updated_at timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT comments_pkey PRIMARY KEY (comment_id),
    CONSTRAINT comments_article_id_fkey FOREIGN KEY (article_id)
        REFERENCES articles (article_id)
        ON UPDATE NO ACTION
        ON DELETE NO ACTION,
    CONSTRAINT comments_user_id_fkey FOREIGN KEY (user_id)
        REFERENCES users (user_id)
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
);