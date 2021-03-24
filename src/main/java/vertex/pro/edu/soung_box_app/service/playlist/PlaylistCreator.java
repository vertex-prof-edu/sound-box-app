package vertex.pro.edu.soung_box_app.service.playlist;

import vertex.pro.edu.soung_box_app.entity.playlist.PlaylistEntity;
import vertex.pro.edu.soung_box_app.entity.playlist.model.Playlist;
import vertex.pro.edu.soung_box_app.exception.UserDoesntExistException;
import vertex.pro.edu.soung_box_app.exception.UserNotConfirmedException;

public interface PlaylistCreator {
    void createDefaultPlaylist(PlaylistEntity playlistEntity);

    Playlist createPlaylist(String name) throws UserDoesntExistException, UserNotConfirmedException;
}
