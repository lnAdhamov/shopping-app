package uz.pdp.shopping_web_app.config;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import lombok.Getter;

public class ConnectionPoolManager {
    @Getter
    private static final HikariDataSource dataSource;

    static {
        try {
            Class.forName("org.postgresql.Driver");
            HikariConfig config = new HikariConfig();
            config.setJdbcUrl("jdbc:postgresql://localhost:5432/g35_shopping_web_app");
            config.setUsername("postgres");
            config.setPassword("muhsin07");
            config.setMinimumIdle(5);
            config.setMaximumPoolSize(10);
            config.setConnectionTimeout(30000);
            dataSource = new HikariDataSource(config);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

}
