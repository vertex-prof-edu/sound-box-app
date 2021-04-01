package vertex.pro.edu.soung_box_app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import vertex.pro.edu.soung_box_app.entity.playlist.PlaylistEntity;
import vertex.pro.edu.soung_box_app.entity.user.UserEntity;

import java.util.List;

public interface PlaylistRepository extends JpaRepository<PlaylistEntity, String> {
    @Query(value = "select * from playlists where user_id = :id", nativeQuery = true)
    List<PlaylistEntity> showAllUserPlaylists(@Param("id") String id);

    @Query(value = "select * from playlists where (name = :name) + (created_at = :created_at)" +
            "and (user_id = :id)", nativeQuery = true)
    PlaylistEntity findByParams(@Param("name") String name, @Param("created_at") String createdAt,
                                @Param("id") UserEntity id);

    @Query(value = "select * from playlists where name = :name", nativeQuery = true)
    PlaylistEntity findByName(@Param("name") String name);
}
