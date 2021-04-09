package vertex.pro.edu.soung_box_app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import vertex.pro.edu.soung_box_app.entity.song.SongEntity;

import java.util.List;

public interface SongRepository extends JpaRepository<SongEntity, String> {

    @Query(value="SELECT * FROM songs WHERE (:genre is null or genre = :genre) " +
            "AND (:artist IS NULL or artist = :artist)", nativeQuery=true)
    List<SongEntity> findByParams(@Param("genre") String genre, @Param("artist") String artist);

//    @Query(value="SELECT * FROM songs WHERE (id = :id) + (title =:title) + (genre = :genre) " +
//            "AND (artist = :artist) + (release_date = :release_date)", nativeQuery=true)
//    SongEntity findByParamsForPlaylist(@Param("id") String id, @Param("title") String title,
//                                       @Param("genre") String genre, @Param("artist") String artist,
//                                       @Param("release_date") String releaseDate);
}

