CREATE TABLE users_with_email
(
    ID              VARCHAR(255) not null,
    USER_ROLE       VARCHAR(255) not null,
    USERNAME        VARCHAR(255) not null,
    EMAIL           VARCHAR(255) not null,
    PASSWORD        VARCHAR(255) not null,
    RELEASE_DATE    TIMESTAMP,
    PRIMARY KEY (ID)
);

CREATE TABLE confirmation_token
(
    ID              VARCHAR(255) not null,
    TOKEN           VARCHAR(255) not null,
    CREATED_AT      VARCHAR(255) not null,
    EXPIRES_AT      VARCHAR(255) not null,
    USER_ID         VARCHAR(255) not null,
    PRIMARY KEY (ID)
);