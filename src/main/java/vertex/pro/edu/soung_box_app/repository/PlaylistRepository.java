package vertex.pro.edu.soung_box_app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import vertex.pro.edu.soung_box_app.entity.playlist.PlaylistEntity;

import java.util.List;

public interface PlaylistRepository extends JpaRepository<PlaylistEntity, String> {

//    @Query(value = "select * from playlists where name = :name", nativeQuery = true)
//    Optional<Playlist> findByName(@Param("name") String name);

    @Query(value = "select * from playlists where user_id = :id", nativeQuery = true)
    List<PlaylistEntity> showAllUserPlaylists(@Param("id") String id);
}
