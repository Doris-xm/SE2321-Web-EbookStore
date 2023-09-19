package com.example.ebook_back;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class EbookBackApplication {
    public static void main(String[] args) {
        SpringApplication.run(EbookBackApplication.class, args);
    }

}
