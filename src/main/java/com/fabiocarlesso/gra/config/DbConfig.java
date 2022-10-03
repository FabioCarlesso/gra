package com.fabiocarlesso.gra.config;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.fabiocarlesso.gra.service.DbService;

@Configuration
public class DbConfig {
    
    @Autowired
    private DbService dbService;

    @Bean
    public boolean instantiateDatabase() throws IOException {
        dbService.instantiateDatabase();
        return true;
    }

}
