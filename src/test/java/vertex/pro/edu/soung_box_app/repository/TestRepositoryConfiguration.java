package vertex.pro.edu.soung_box_app.repository;

import vertex.pro.edu.soung_box_app.common.utils.db.TestEmbeddedMySqlConfiguration;
import vertex.pro.edu.soung_box_app.common.utils.db.TestMySqlConfiguration;
import vertex.pro.edu.soung_box_app.entity.SongEntity;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Import;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootConfiguration
@EnableAutoConfiguration
@EntityScan(basePackageClasses = {SongEntity.class})
@EnableJpaRepositories(basePackageClasses = {SongRepository.class})
@EnableConfigurationProperties(value = {TestMySqlConfiguration.class})
@Import(value = {TestEmbeddedMySqlConfiguration.class})
class TestRepositoryConfiguration {
}
