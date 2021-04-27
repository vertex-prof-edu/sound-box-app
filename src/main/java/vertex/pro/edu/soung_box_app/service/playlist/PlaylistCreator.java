package vertex.pro.edu.soung_box_app.service.playlist;

import vertex.pro.edu.soung_box_app.entity.playlist.PlaylistEntity;
import vertex.pro.edu.soung_box_app.entity.playlist.model.Playlist;

public interface PlaylistCreator {
    PlaylistEntity createDefaultLikesPlaylist() throws Exception;

    PlaylistEntity createDefaultDislikesPlaylist() throws Exception;

    Playlist createPlaylist(String name) throws Exception;
}
