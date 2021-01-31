CREATE TABLE users
(
    ID              VARCHAR(255) not null,
    USER_ROLE       VARCHAR(100) not null,
    USERNAME        VARCHAR(100) not null,
    EMAIL           VARCHAR(100) not null,
    PASSWORD        VARCHAR(100) not null,
    REGISTRATION_DATE    TIMESTAMP,
    PRIMARY KEY (USERNAME)
);

CREATE TABLE confirmation_token
(
    ID              VARCHAR(255) not null,
    TOKEN           VARCHAR(100) not null,
    CREATED_AT      VARCHAR(100) not null,
    EXPIRES_AT      VARCHAR(100) not null,
    USER_USERNAME   VARCHAR(100) not null,
    PRIMARY KEY (ID)
);

CREATE TABLE songs
(
    ID           VARCHAR(255) not null,
    TITLE        VARCHAR(100) not null,
    ARTIST       VARCHAR(100) not null,
    ALBUM        VARCHAR(100) not null,
    GENRE        VARCHAR(100) not null,
    RELEASE_DATE TIMESTAMP,
    PRIMARY KEY (ID)
);

alter table confirmation_token
    add constraint USER_USERNAME
    foreign key (USER_USERNAME)
    references users (USERNAME);



