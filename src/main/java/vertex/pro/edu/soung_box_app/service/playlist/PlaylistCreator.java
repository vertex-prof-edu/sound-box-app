package vertex.pro.edu.soung_box_app.service.playlist;

import vertex.pro.edu.soung_box_app.entity.playlist.Playlist;

public interface PlaylistCreator {
    void createDefaultPlaylist(Playlist playlist);

    String createPlaylist(Playlist playlist);
}
