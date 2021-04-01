package vertex.pro.edu.soung_box_app.controller.request;

import lombok.Data;

@Data
public class AddSongToPlaylistRequest {
    protected final String playlistName;
    protected final String playlistCreatedAt;
    protected final String id;
    protected final String title;
    protected final String genre;
    protected final String artist;
    protected final String releaseSongDate;
}
