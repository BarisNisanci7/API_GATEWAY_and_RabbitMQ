package com.api_gateway;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ApiGatewayApplicationTests {

	@Autowired
	private TestRestTemplate restTemplate;

	@Test
	public void testUserRoute() {
		ResponseEntity<String> response = restTemplate.getForEntity("/users", String.class);
		assertThat(response.getStatusCode().is2xxSuccessful()).isTrue();
		System.out.println("User route response: " + response.getBody());
	}

	@Test
	public void testFlightRoute() {
		ResponseEntity<String> response = restTemplate.getForEntity("/flights", String.class);
		assertThat(response.getStatusCode().is2xxSuccessful()).isTrue();
		System.out.println("Flight route response: " + response.getBody());
	}

	@Test
	public void testTicketRoute() {
		ResponseEntity<String> response = restTemplate.getForEntity("/tickets", String.class);
		assertThat(response.getStatusCode().is2xxSuccessful()).isTrue();
		System.out.println("Ticket route response: " + response.getBody());
	}
}