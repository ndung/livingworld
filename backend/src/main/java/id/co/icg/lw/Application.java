package id.co.icg.lw;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;

@SpringBootApplication
public class Application{
    public static final String AUTH = "Authorization";
    public static final String API_PATH = "/api/v1";

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Bean
    @ConfigurationProperties(prefix="parking.datasource")
    public DataSource parkingDataSource() {
        return DataSourceBuilder.create().build();
    }

    @Bean
    public JdbcTemplate parkingJdbcTemplate(){
        return new JdbcTemplate(parkingDataSource());
    }
}
