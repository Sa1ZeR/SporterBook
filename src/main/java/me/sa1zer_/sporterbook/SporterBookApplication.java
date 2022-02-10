package me.sa1zer_.sporterbook;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class SporterBookApplication {

    public static void main(String[] args) {
        SpringApplication.run(SporterBookApplication.class, args);
    }

    @PostMapping("")
    public ResponseEntity<?> test() {
        return null;
    }
}
