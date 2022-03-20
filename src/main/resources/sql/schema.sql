DROP TABLE IF EXISTS users;

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
    is_resolved bool                  NOT NULL
);