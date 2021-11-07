package inc.popug.central.config;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;

@EnableConfigServer
@SpringBootApplication
public class ATesCentralConfigurationApplication {

    public static void main(String[] args) {
        SpringApplication.run(ATesCentralConfigurationApplication.class, args);
    }

}
