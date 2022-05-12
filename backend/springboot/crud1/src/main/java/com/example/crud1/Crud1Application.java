package com.example.crud1;

import com.example.crud1.repository.DocumentRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(exclude={DataSourceAutoConfiguration.class})
public class Crud1Application {
    public static void main(String[] args) {
        SpringApplication.run(Crud1Application.class, args);
    }

}
