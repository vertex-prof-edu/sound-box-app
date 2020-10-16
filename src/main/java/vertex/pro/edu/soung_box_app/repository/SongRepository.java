package vertex.pro.edu.soung_box_app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import vertex.pro.edu.soung_box_app.entity.SongEntity;

import java.util.List;

public interface SongRepository extends JpaRepository<SongEntity, String> {

    @Query("from SongEntity where genre = :genre or :genre is null")
    List<SongEntity> findByParams(@Param("genre") String genre);
}
