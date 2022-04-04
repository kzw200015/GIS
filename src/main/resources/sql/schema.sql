DROP TABLE IF EXISTS users;
DROP TABLE IF EXISTS accident_polices;
DROP TABLE IF EXISTS accidents;
DROP TABLE IF EXISTS polices;
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
    description TEXT,
    time        TIMESTAMP             NOT NULL,
    is_resolved BOOLEAN               NOT NULL
);

CREATE TABLE polices
(
    id     SERIAL PRIMARY KEY,
    name   VARCHAR(10) NOT NULL,
    gender INT         NOT NULL,
    phone  VARCHAR(50)
);

CREATE TABLE accident_polices
(
    accident_id SERIAL NOT NULL REFERENCES accidents (id),
    police_id   SERIAL NOT NULL REFERENCES polices (id)
);