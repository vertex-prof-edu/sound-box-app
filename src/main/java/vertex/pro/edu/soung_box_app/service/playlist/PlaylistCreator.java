package vertex.pro.edu.soung_box_app.service.playlist;


import vertex.pro.edu.soung_box_app.entity.playlist.PlaylistEntity;
import vertex.pro.edu.soung_box_app.entity.playlist.model.Playlist;
import vertex.pro.edu.soung_box_app.exception.UserDoesntExistException;
import vertex.pro.edu.soung_box_app.exception.UserNotConfirmedException;

import java.util.List;

public interface PlaylistCreator {
    PlaylistEntity createDefaultPlaylist() throws Exception;

    Playlist createPlaylist(String name) throws Exception;
}
