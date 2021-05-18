package vertex.pro.edu.soung_box_app.service.artist;

import vertex.pro.edu.soung_box_app.controller.request.body.SongRequest;
import vertex.pro.edu.soung_box_app.entity.song.SongEntity;

public interface ArtistService {

    String becameAnArtist();

    SongEntity addNewSong(SongRequest song);

    String removeAddedSong(String songId);
}
