package vertex.pro.edu.soung_box_app.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import vertex.pro.edu.soung_box_app.entity.playlist.PlaylistEntity;

import java.util.List;

@Repository
public interface PlaylistRepository extends PagingAndSortingRepository<PlaylistEntity, String> {
    @Query(value = "select * from playlists where user_id = :id", nativeQuery = true)
    List<PlaylistEntity> showAllUserPlaylists(@Param("id") String id);

//    @Query("select * from pla p join UserEntity u where u.id = :id")
//    List<PlaylistEntity> findAllUserPlaylists(@Param("id") String id);

    @Query(value = "select * from playlists where playlist_title = :playlist_title", nativeQuery = true)
    PlaylistEntity findByName(@Param("playlist_title") String playlist_title);
}


