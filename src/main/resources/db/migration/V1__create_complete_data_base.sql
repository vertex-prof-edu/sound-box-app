CREATE TABLE users
(
    ID              VARCHAR(255) not null PRIMARY KEY,
    USER_ROLE       VARCHAR(100) not null,
    USERNAME        VARCHAR(100) not null,
    EMAIL           VARCHAR(100) not null,
    PASSWORD        VARCHAR(100) not null,
    REGISTRATION_DATE    TIMESTAMP,
    ENABLED          VARCHAR(100)
);

CREATE TABLE confirmation_token
(
    ID              VARCHAR(255) not null PRIMARY KEY,
    TOKEN           VARCHAR(100) not null,
    CREATED_AT      VARCHAR(100) not null,
    EXPIRES_AT      VARCHAR(100) not null,
    USER_ID         VARCHAR(100) not null,
    CONFIRMED_AT    VARCHAR(100),
    CONSTRAINT USER_ID_FK FOREIGN KEY (USER_ID) REFERENCES users (ID)
);

CREATE TABLE songs
(
    ID           VARCHAR(255) not null PRIMARY KEY,
    TITLE        VARCHAR(100) not null,
    ARTIST       VARCHAR(100) not null,
    ALBUM        VARCHAR(100) not null,
    GENRE        VARCHAR(100) not null,
    RELEASE_DATE TIMESTAMP
);

CREATE TABLE playlists
(
    ID              VARCHAR(255) not null PRIMARY KEY,
    NAME            VARCHAR(100) not null,
    USER_ID         VARCHAR(100) not null,
    CONSTRAINT PLAYLIST_USER_ID_FK FOREIGN KEY (USER_ID) REFERENCES users (ID)
);

CREATE TABLE playlist_song
(
    ID              VARCHAR(255) not null PRIMARY KEY,
    SONG_ID         VARCHAR(100) not null,
    PLAYLIST_ID     VARCHAR(100) not null,
    CONSTRAINT PLAYLIST_ID_FK FOREIGN KEY (PLAYLIST_ID) REFERENCES playlists (ID),
    CONSTRAINT SONG_ID_FK FOREIGN KEY (SONG_ID) REFERENCES songs (ID)
);




