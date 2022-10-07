package com.fabiocarlesso.gra.controller.rest;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.fabiocarlesso.gra.domain.dto.MovieProducerMinMaxDTO;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@TestPropertySource(locations = "classpath:application-integrationtest.properties")
public class MovieControllerIntegrationTest {
    
    private static final String HOST = "http://localhost:";
    private static final String MOVIES_PATH = "/v1/movies";
    private static final String INTERVAL_PATH = "/interval";
    
    @LocalServerPort
    private int port;

    private String path;

    @BeforeEach
    private void init() {
        this.path = HOST+port+MOVIES_PATH;
    }
	
    @Test
	public void findMinMaxIntervalTest() {
		TestRestTemplate testRestTemplate = new TestRestTemplate();
		ResponseEntity<MovieProducerMinMaxDTO> response = testRestTemplate.getForEntity(
			this.path+INTERVAL_PATH, MovieProducerMinMaxDTO.class);
        //Success
		Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
        //Min interval
        Assertions.assertEquals(1, response.getBody().getMin().get(0).getInterval());
        //Max interval
        Assertions.assertEquals(13, response.getBody().getMax().get(1).getInterval());
        //Body not null
		Assertions.assertNotNull(response.getBody());
        //Min not null
        Assertions.assertNotNull(response.getBody().getMin());
        //Max not null
        Assertions.assertNotNull(response.getBody().getMax());
	}
}
