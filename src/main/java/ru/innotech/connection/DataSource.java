
package ru.innotech.connection;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.SQLException;

@Component
public class DataSource {
    private  static HikariConfig config = new HikariConfig();
    private static HikariDataSource ds;

    @Autowired
    public DataSource(@Value("${datasource.url}") String url, @Value("${datasource.username}")String username, @Value("${datasource.password}")String password) {
        config.setJdbcUrl(url);
        config.setUsername(username);
        config.setPassword(password);
        ds = new HikariDataSource(config);
    }

    @SneakyThrows
    @Bean
    public static Connection getConnection() {
        return ds.getConnection();
    }
}
