package vertex.pro.edu.soung_box_app.service.song;

import vertex.pro.edu.soung_box_app.entity.song.SongEntity;

import java.util.List;

public interface SongFinder {
    List<SongEntity> getSongs(String genre, String artist);
}
