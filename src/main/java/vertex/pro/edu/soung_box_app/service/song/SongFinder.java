package vertex.pro.edu.soung_box_app.service.song;

import vertex.pro.edu.soung_box_app.entity.song.model.Song;

import java.util.List;

public interface SongFinder {
    List<Song> getSongs(String genre, String artist);
}
