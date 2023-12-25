package brianbig.transcend;

import brianbig.transcend.config.JWTConfigProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties(JWTConfigProperties.class)
public class Main {
    public static void main(String[] args) {
        SpringApplication.run(Main.class);
    }
}