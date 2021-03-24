package vertex.pro.edu.soung_box_app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import vertex.pro.edu.soung_box_app.entity.song.SongEntity;

import java.util.List;
import java.util.Optional;

public interface SongRepository extends JpaRepository<SongEntity, String> {

    @Query(value="SELECT * FROM songs WHERE (:genre is null or genre = :genre) " +
            "AND (:artist IS NULL or artist = :artist)", nativeQuery=true)
    List<SongEntity> findByParams(@Param("genre") String genre, @Param("artist") String artist);

    @Query(value = "SELECT * FROM songs WHERE title = :title", nativeQuery = true)
    List<SongEntity> findByName(@Param("title") String title);
}