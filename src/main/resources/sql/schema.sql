DROP TABLE IF EXISTS accident_users;
DROP TABLE IF EXISTS polices;
DROP TABLE IF EXISTS users;
DROP TABLE IF EXISTS accidents;
DROP EXTENSION IF EXISTS postgis;

CREATE EXTENSION postgis;

CREATE TABLE users
(
    id       SERIAL PRIMARY KEY,
    username VARCHAR(50)  NOT NULL UNIQUE,
    password VARCHAR(200) NOT NULL,
    role     INT          NOT NULL
);

CREATE TABLE accidents
(
    id          SERIAL PRIMARY KEY,
    coordinate  geometry(POINT, 4326) NOT NULL,
    description TEXT                  NULL,
    time        TIMESTAMP             NOT NULL,
    is_resolved BOOLEAN               NOT NULL,
    image       VARCHAR(200)          NULL
);

CREATE TABLE accident_users
(
    accident_id SERIAL NOT NULL REFERENCES accidents (id),
    user_id     SERIAL NOT NULL REFERENCES users (id)
);

CREATE TABLE polices
(
    id         SERIAL PRIMARY KEY,
    name       VARCHAR(10) NOT NULL,
    gender     INT         NOT NULL,
    phone      VARCHAR(50) NULL,
    is_working BOOLEAN     NOT NULL,
    user_id    SERIAL      NOT NULL REFERENCES users (id)
)