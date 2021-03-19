package vertex.pro.edu.soung_box_app.service.playlist;

import vertex.pro.edu.soung_box_app.entity.playlist.Playlist;
import vertex.pro.edu.soung_box_app.exception.UserDoesntExistException;

public interface PlaylistCreator {
    void createDefaultPlaylist(Playlist playlist);

    String createPlaylist(String name) throws UserDoesntExistException;
}
