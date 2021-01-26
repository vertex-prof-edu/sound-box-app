CREATE TABLE users_with_email
(
    ID              VARCHAR(255) not null,
    USER_ROLE       VARCHAR(100) not null,
    USERNAME        VARCHAR(100) not null,
    EMAIL           VARCHAR(100) not null,
    PASSWORD        VARCHAR(100) not null,
    RELEASE_DATE    TIMESTAMP,
    PRIMARY KEY (ID)
);

CREATE TABLE confirmation_token
(
    ID              VARCHAR(255) not null,
    TOKEN           VARCHAR(100) not null,
    CREATED_AT      VARCHAR(100) not null,
    EXPIRES_AT      VARCHAR(100) not null,
    USER_ID         VARCHAR(100) not null,
    PRIMARY KEY (ID)
);