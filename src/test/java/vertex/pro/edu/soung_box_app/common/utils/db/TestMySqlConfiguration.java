package vertex.pro.edu.soung_box_app.common.utils.db;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.util.SocketUtils;

@ConfigurationProperties(prefix = "test.database.mysql")
@Getter
@Setter
public class TestMySqlConfiguration {
    private String username;
    private String password;
    private String schemaName;
    private String driverName;
    private int port = SocketUtils.findAvailableTcpPort();
}