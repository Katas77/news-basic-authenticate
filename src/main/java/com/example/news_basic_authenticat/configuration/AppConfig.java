package com.example.news_basic_authenticat.configuration;


import com.example.news_basic_authenticat.init.DatabaseInit;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {
    @Bean
    CommandLineRunner commandLineRunner(DatabaseInit scriptExecutor) {
        return args -> {
            scriptExecutor.executeSqlFile("data.sql");
        };
    }
}