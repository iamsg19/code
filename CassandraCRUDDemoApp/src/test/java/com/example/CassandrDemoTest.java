//package com.example;
//
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.http.MediaType;
//import org.springframework.test.context.junit.jupiter.SpringExtension;
//import org.springframework.test.web.reactive.server.WebTestClient;
//	
//@ExtendWith(SpringExtension.class)
//@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
//public class CassandrDemoTest {
//
//	@Autowired
//	private WebTestClient webTestClient;
//	
//	@Test
//	public void testCassandra() {
//		
//		webTestClient
//			.get().uri("/api/all/students")
//			.accept(MediaType.APPLICATION_JSON)
//			.exchange()
//			.expectStatus().isOk();
//	}
//}
