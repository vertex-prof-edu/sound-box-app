package vertex.pro.edu.soung_box_app.repository;

import vertex.pro.edu.soung_box_app.entity.SongEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SongRepository extends JpaRepository<SongEntity, String> {

}
