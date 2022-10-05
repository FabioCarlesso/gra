package com.fabiocarlesso.gra;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.fabiocarlesso.gra.domain.dto.MovieProducerMinMaxDTO;

@SpringBootTest
class GraApplicationTests {

	private static final String HOST = "http://localhost:";
	private static final String MOVIES_PATH = "/v1/movies";
	private static final String INTERVAL_PATH = "interval";

	private String path;

	@LocalServerPort
    private int port;

	private TestRestTemplate testRestTemplate;

	@BeforeEach
    private void init() {
        this.path = HOST+port+MOVIES_PATH;
    }
	
	@Test
	public void findMinMaxIntervalTest() {
		TestRestTemplate testRestTemplate = new TestRestTemplate();
		ResponseEntity<MovieProducerMinMaxDTO> response = testRestTemplate.getForEntity(
			this.path+INTERVAL_PATH, MovieProducerMinMaxDTO.class);
		Assertions.assertEquals(response.getStatusCode(), HttpStatus.OK);
		Assertions.assertNotNull(response.getBody());
	}

}
