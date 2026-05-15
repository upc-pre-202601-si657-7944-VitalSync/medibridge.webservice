package pe.edu.upc.vitalsync.medibridge;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableJpaAuditing
public class BackendMediBridgeApplication {

    public static void main(String[] args) {
        SpringApplication.run(BackendMediBridgeApplication.class, args);
    }

}
