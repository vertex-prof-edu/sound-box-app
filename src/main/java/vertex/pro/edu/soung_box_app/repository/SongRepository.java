package vertex.pro.edu.soung_box_app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import vertex.pro.edu.soung_box_app.entity.SongEntity;

import java.util.List;

import static org.hibernate.hql.internal.antlr.SqlTokenTypes.IS;
import static org.hibernate.hql.internal.antlr.SqlTokenTypes.WHERE;

public interface SongRepository extends JpaRepository<SongEntity, String> {
    @Query(value = "SELECT * FROM songs WHERE ((genre = :genre OR :genre is null) OR (artist = :artist OR :artist is null))" +
            "or ((genre = :genre OR :genre is null) AND (artist = :artist OR :artist is null))", nativeQuery=true)
    List<SongEntity> findByParams(@Param("genre") String genre, @Param("artist") String artist);
}
