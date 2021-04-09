package vertex.pro.edu.soung_box_app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import vertex.pro.edu.soung_box_app.entity.playlist.PlaylistEntity;

import java.util.List;

public interface PlaylistRepository extends JpaRepository<PlaylistEntity, String> {
    @Query(value = "select * from playlists where user_id = :id", nativeQuery = true)
    List<PlaylistEntity> showAllUserPlaylists(@Param("id") String id);

//    @Query(value = "select * from playlists where (name = :name) + (created_at = :created_at)" +
//            "and (user_id = :id)", nativeQuery = true)
//    PlaylistEntity findByParams(@Param("name") String name, @Param("created_at") String createdAt,
//                                @Param("id") UserEntity id);

    @Query(value = "select * from playlists where playlist_title = :playlist_title", nativeQuery = true)
    PlaylistEntity findByName(@Param("playlist_title") String playlist_title);

    @Query(value = "select p.id = :playlistId as id, p.playlist_title, ps.song_id, s.title " +
            "from playlists p " +
            "join playlist_song ps on (p.id = ps.playlist_id) " +
            "join songs s on (ps.song_id = s.id) " +
            "where user_id = :user_id ", nativeQuery = true)
    List<PlaylistEntity> showUserPlaylistsWithSongs(@Param("user_id") String user_id,
                                                    @Param("playlistId") String playlistId);
}


