package org.angularsecurity;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
@EntityScan("org.angularsecurity.domain")
public class AngularSecurityRestOauthApplication {

    public static void main(String[] args) {
        SpringApplication.run(AngularSecurityRestOauthApplication.class, args);
    }
}
