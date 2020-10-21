package vertex.pro.edu.soung_box_app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import vertex.pro.edu.soung_box_app.entity.SongEntity;

import java.util.List;

import static org.hibernate.hql.internal.antlr.SqlTokenTypes.IS;
import static org.hibernate.hql.internal.antlr.SqlTokenTypes.WHERE;

public interface SongRepository extends JpaRepository<SongEntity, String> {
//        @Query("from SongEntity where (genre = :genre or :genre is null)")
//        List<SongEntity> findByParams(@Param("genre") String genre);

//    @Query(value = "SELECT * FROM songs WHERE (gener = :gener OR genre IS NULL) AND (artist = :artist OR artist IS NULL)", nativeQuery=true)
//    List<SongEntity> findByParams(@Param("genre") String genre, @Param("artist") String artist);



    @Query("from SongEntity where (genre = :genre or :genre is null) or (artist = :artist or :artist is null)" +
            "or ((genre = :genre or :genre is null) and (artist = :artist or :artist is null))")
    List<SongEntity> findByParams(@Param("genre") String genre, @Param("artist") String artist);


//    @Query(value = "SELECT * FROM songs WHERE (genre = :genre OR :genre LIKE '%' AND artist = :artist OR :artist LIKE '%')", nativeQuery=true)


}



