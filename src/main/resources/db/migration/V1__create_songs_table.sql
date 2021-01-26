create table songs
(
    ID           VARCHAR(255) not null,
    TITLE        VARCHAR(100) not null,
    ARTIST       VARCHAR(100) not null,
    ALBUM        VARCHAR(100) not null,
    GENRE        VARCHAR(100) not null,
    RELEASE_DATE TIMESTAMP,
    PRIMARY KEY (ID)
);