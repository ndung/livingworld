package id.co.icg.lw;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@EnableAutoConfiguration
@SpringBootApplication
@EnableTransactionManagement
public class Application {
    public static final String AUTH = "Authorization";
    public static final String API_PATH = "/api/v1";
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
