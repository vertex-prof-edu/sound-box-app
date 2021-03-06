ALTER TABLE playlists DROP FOREIGN KEY PLAYLIST_USER_ID_FK;
ALTER TABLE playlists ADD CONSTRAINT PLAYLIST_USER_ID_FK FOREIGN KEY (USER_ID) REFERENCES users (ID) ON UPDATE CASCADE ON DELETE CASCADE;

ALTER TABLE playlist_song DROP FOREIGN KEY PLAYLIST_ID_FK, DROP FOREIGN KEY SONG_ID_FK;
ALTER TABLE playlist_song ADD CONSTRAINT PLAYLIST_ID_FK FOREIGN KEY (PLAYLIST_ID) REFERENCES playlists (ID) ON UPDATE CASCADE ON DELETE CASCADE,
                          ADD CONSTRAINT SONG_ID_FK FOREIGN KEY (SONG_ID) REFERENCES songs (ID) ON UPDATE CASCADE ON DELETE CASCADE;

ALTER TABLE subscription DROP FOREIGN KEY SUBSCRIPTION_USER_ID_FK;
ALTER TABLE subscription ADD CONSTRAINT SUBSCRIPTION_USER_ID_FK FOREIGN KEY (USER_ID) REFERENCES users (ID) ON UPDATE CASCADE ON DELETE CASCADE;

ALTER TABLE subscription_song DROP FOREIGN KEY SUBSCRIPTION_ID_FK, DROP FOREIGN KEY SUB_SONG_ID_FK;
ALTER TABLE subscription_song ADD CONSTRAINT SUBSCRIPTION_ID_FK FOREIGN KEY (SUBSCRIPTION_ID) REFERENCES subscription (ID) ON UPDATE CASCADE ON DELETE CASCADE,
                              ADD CONSTRAINT SUB_SONG_ID_FK FOREIGN KEY (SONG_ID) REFERENCES songs (ID) ON UPDATE CASCADE ON DELETE CASCADE;