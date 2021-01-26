create table users
(
    ID           VARCHAR(255) not null,
    USERNAME     VARCHAR(100) not null,
    PASSWORD     VARCHAR(100) not null,
    RELEASE_DATE TIMESTAMP,
    PRIMARY KEY (ID)
);