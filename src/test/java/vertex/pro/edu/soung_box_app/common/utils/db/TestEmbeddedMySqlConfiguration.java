package vertex.pro.edu.soung_box_app.common.utils.db;

import com.wix.mysql.EmbeddedMysql;
import com.wix.mysql.config.MysqldConfig;
import com.wix.mysql.config.SchemaConfig;
import com.wix.mysql.distribution.Version;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;
import java.util.concurrent.TimeUnit;

import static com.wix.mysql.config.Charset.UTF8;
import static com.wix.mysql.config.SchemaConfig.aSchemaConfig;

@Configuration
@EnableConfigurationProperties(value = {TestMySqlConfiguration.class})
public class TestEmbeddedMySqlConfiguration {

    @Bean
    public MysqldConfig mysqldConfig(TestMySqlConfiguration dbConfig) {
        return MysqldConfig.aMysqldConfig(Version.v5_7_latest)
                .withCharset(UTF8)
                .withPort(dbConfig.getPort())
                .withUser(dbConfig.getUsername(), dbConfig.getPassword())
                .withTimeZone("Europe/Vilnius")
                .withTimeout(2, TimeUnit.MINUTES)
                .withServerVariable("max_connect_errors", 666)
                .withServerVariable("sql-mode","")
                .build();
    }

    @Bean
    public SchemaConfig schemaConfig(TestMySqlConfiguration dbConfig) {
        return aSchemaConfig(dbConfig.getSchemaName())
                .withCharset(UTF8)
                .build();
    }

    @Bean
    public EmbeddedMysql embeddedMysql(SchemaConfig schemaConfig,
                                       MysqldConfig mysqldConfig) {
        return EmbeddedMysql.anEmbeddedMysql(mysqldConfig)
                .addSchema(schemaConfig)
                .start();
    }

    @Bean
    @DependsOn(value = {"embeddedMysql"})
    public DataSource dataSource(TestMySqlConfiguration dbConfig) {
        String url = String.format("jdbc:mysql://localhost:%d/%s",
                dbConfig.getPort(),
                dbConfig.getSchemaName());

        DriverManagerDataSource dataSource = new DriverManagerDataSource(
                url,
                dbConfig.getUsername(),
                dbConfig.getPassword());

        dataSource.setDriverClassName(dbConfig.getDriverName());

        return dataSource;
    }
}
