package vertex.pro.edu.soung_box_app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import vertex.pro.edu.soung_box_app.entity.playlist.Playlist;

import java.util.Optional;

public interface PlaylistRepository extends JpaRepository<Playlist, String> {

    @Query(value = "select * from playlists where name = :name", nativeQuery = true)
    Optional<Playlist> findByName(@Param("name") String name);
}
