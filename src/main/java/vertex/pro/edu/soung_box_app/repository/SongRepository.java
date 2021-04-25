package vertex.pro.edu.soung_box_app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import vertex.pro.edu.soung_box_app.entity.song.SongEntity;

import java.util.List;

@Repository
public interface SongRepository extends JpaRepository<SongEntity, String> {

    @Query(value="SELECT * FROM songs WHERE (:genre is null or genre = :genre) " +
            "AND (:artist IS NULL or artist = :artist)", nativeQuery=true)
    List<SongEntity> findByParams(@Param("genre") String genre, @Param("artist") String artist);

    @Query(value="SELECT * FROM songs WHERE artist = :artist", nativeQuery=true)
    List<SongEntity> findByArtist(@Param("artist") String artist);

    @Query(value="SELECT * FROM songs WHERE genre = :genre", nativeQuery=true)
    List<SongEntity> findByGenre(@Param("genre") String genre);

    @Query(value = "SELECT s.title, s.likes FROM SongEntity s WHERE s.artist = :artist")
    List<SongEntity> showSongsStatistics(@Param("artist") String artist);
}

