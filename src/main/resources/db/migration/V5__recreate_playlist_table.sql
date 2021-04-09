ALTER TABLE `sound-box-app`.`playlist_song`
DROP FOREIGN KEY `PLAYLIST_ID_FK`;
ALTER TABLE `sound-box-app`.`playlist_song`
DROP INDEX `PLAYLIST_ID_FK` ;

DROP TABLE playlists;

CREATE TABLE playlists
(
    ID              VARCHAR(255) not null PRIMARY KEY,
    PLAYLIST_TITLE  VARCHAR(100) not null,
    USER_ID         VARCHAR(100) not null,
    CREATED_AT VARCHAR(100) not null,
    CONSTRAINT PLAYLIST_USER_ID_FK FOREIGN KEY (USER_ID) REFERENCES users (ID)
);