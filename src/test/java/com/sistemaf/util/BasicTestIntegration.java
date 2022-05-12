package com.sistemaf.util;

import org.flywaydb.core.Flyway;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.context.TestPropertySource;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.springframework.test.context.junit4.SpringRunner;
import org.testcontainers.containers.MariaDBContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;


@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestPropertySource("/application-test.properties")
@Testcontainers
public class BasicTestIntegration {

	@Autowired
	protected Flyway flyway;

	@LocalServerPort
	protected int port;

	@Autowired
	private  DatabaseCleaner databaseCleaner;

	@Container
	public static MariaDBContainer mariaDB = new MariaDBContainer("mariadb:10.5.8");

	protected String accessToken;

	@DynamicPropertySource
	public static void overridProps(DynamicPropertyRegistry registry) {
		mariaDB.start();
		registry.add("spring.datasource.url", mariaDB::getJdbcUrl);
		registry.add("spring.datasource.username", mariaDB::getUsername);
		registry.add("spring.datasource.password", mariaDB::getPassword);
	}

	@Before
	public void setupTest() {
		RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
		RestAssured.port = port;
		databaseCleaner.clearTables();
		flyway.migrate();
	}


	public String getAccessToken() {
		return RestAssured.given()
				.basePath("/oauth")
			.auth()
				.preemptive().basic("angular", "@ngul@r0")
			.contentType(ContentType.URLENC)
				.formParam("grant_type", "password")
				.formParam("client", "angular")
				.formParam("username", "admin")
				.formParam("password", "admin")
		.when()
			.post("/token")
			.as(OAuth2AccessToken.class).getValue();
	}
}
