package com.aws.rest;

import io.restassured.module.mockmvc.RestAssuredMockMvc;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.context.WebApplicationContext;

import static org.hamcrest.Matchers.is;
/**
 * @SpringBootTest
 * This annotation tells Spring Boot to look for a main configuration class(normally one with the @SpringBootApplication annotation) and use that to start a Spring application context
 * We can specify that Spring Boot should start the server with a random port for testing by adding the following after the annotation: (webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
 * Specifying a random port is useful because like this we can avoid conflicts in test environments

 */
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class MainControllerTest {

    private final String url = "/api/items";

    /**
     * @Autowired is used here to inject the WebApplicationContext before the tests are run
     */
    @Autowired
    private WebApplicationContext webApplicationContext;

    /**
     * @BeforeEach can be used to specify what should be done before each test
     */
    @BeforeEach
    void setUp(){
        RestAssuredMockMvc.webAppContextSetup(webApplicationContext);
    }
    /**
     * @AfterEach can be used to specify what should be done before each test
     */
    @AfterEach
    void cleanUp() {
        RestAssuredMockMvc.reset();
    }
    /**
     * @Test indicates to Spring that this is a test
     */
    @Test
    void getItems() {
        RestAssuredMockMvc.given()
                .contentType("application/json")
                .when()
                .get("/api/items")
                .then()
                .log().ifValidationFails()
                .body("[0].id", is("asdfghjkertzui"))
                .body("[0].guide", is("python"))
                .body("[0].description", is("Need to write SQS examples"))
                .body("[0].status", is("started"))
                .body("[0].archived", is(1));
    }

    @Test
    void modUser() {
        RestAssuredMockMvc.given()
                //.contentType("application/json")
                .when()
                .put("/api/items/asdfghjkertzui:archive")
                .then()
                .log().ifValidationFails()
                .body(is("asdfghjkertzui was archived"));

    }

    @Test
    void addItem() {
        RestAssuredMockMvc.given()
                .contentType("application/json")
                .body("{\"name\":\"user\",\"guide\":\"node js\",\"date\": \"2023-01-06\",\"description\":\"working on it\",\"status\":\"started\",\"archived\":0}")
                .when()
                .post("/api/items")
                .then()
                .log().ifValidationFails()
                .body("[0].guide", is("node js"));

    }
}