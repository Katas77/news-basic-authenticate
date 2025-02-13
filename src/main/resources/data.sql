-- Удаление существующих таблиц (если они есть)
DROP TABLE IF EXISTS comment;
DROP TABLE IF EXISTS news;
DROP TABLE IF EXISTS authorities;
DROP TABLE IF EXISTS our_user;

-- Создание таблицы our_user, если она еще не существует
CREATE TABLE IF NOT EXISTS our_user (
    id BIGSERIAL PRIMARY KEY,
    nickname VARCHAR(255) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL
);

-- Создание таблицы authorities, если она еще не существует
CREATE TABLE IF NOT EXISTS authorities (
    id SERIAL PRIMARY KEY,
    authority VARCHAR(50),
    user_id BIGINT,
    CONSTRAINT fk_authorities_user FOREIGN KEY (user_id) REFERENCES our_user(id)
);

-- Создание таблицы news, если она еще не существует
CREATE TABLE IF NOT EXISTS news (
    id SERIAL PRIMARY KEY,
    category VARCHAR(255),
    text TEXT,
    user_id BIGINT,
    CONSTRAINT fk_news_user FOREIGN KEY (user_id) REFERENCES our_user(id)
);

-- Создание таблицы comment, если она еще не существует
CREATE TABLE IF NOT EXISTS comment (
    id SERIAL PRIMARY KEY,
    text TEXT,
    user_id BIGINT,
    news_id BIGINT,
    CONSTRAINT fk_comment_user FOREIGN KEY (user_id) REFERENCES our_user(id),
    CONSTRAINT fk_comment_news FOREIGN KEY (news_id) REFERENCES news(id)
);

-- Вставка данных в таблицу our_user
INSERT INTO our_user (nickname, password) VALUES ('John Doe', '$2a$12$Qo.O2dD4YKeoisV5UnA1D.I8Vbje8Zb/qiYtD0X/XsLEma41fyx/i');--  password123 passwordEncoder().encode
INSERT INTO our_user (nickname, password) VALUES ('Jane Smith',  '$2a$12$7QqNc203TWoFAdZA7RiyY.btBXpW8JHyekSeJFqLLCDG.xGUKGdZK');-- password456 passwordEncoder().encode


-- Вставка ролей для каждого пользователя
INSERT INTO authorities (authority, user_id) VALUES ('ROLE_USER', 1);
INSERT INTO authorities (authority, user_id) VALUES ('ROLE_ADMIN', 2);


-- Вставка новостей
INSERT INTO news (category, text, user_id) VALUES ('Category1', 'Text for News1', 1);
INSERT INTO news (category, text, user_id) VALUES ('Category2', 'Text for News2', 2);
INSERT INTO news (category, text, user_id) VALUES ('Category4', 'Text for News4', 1);
INSERT INTO news (category, text, user_id) VALUES ('Category5', 'Text for News5', 2);

-- Вставка комментариев
INSERT INTO comment (text, user_id, news_id) VALUES ('Comment Text1', 1, 1);
INSERT INTO comment (text, user_id, news_id) VALUES ('Comment Text2', 2, 1);
INSERT INTO comment (text, user_id, news_id) VALUES ('Comment Text4', 1, 2);
INSERT INTO comment (text, user_id, news_id) VALUES ('Comment Text5', 2, 2);
INSERT INTO comment (text, user_id, news_id) VALUES ('Comment Text7', 1, 3);
INSERT INTO comment (text, user_id, news_id) VALUES ('Comment Text8', 2, 3);
INSERT INTO comment (text, user_id, news_id) VALUES ('Comment Text10', 1, 4);