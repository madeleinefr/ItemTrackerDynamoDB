package com.aws.rest;

import io.restassured.module.mockmvc.RestAssuredMockMvc;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.context.WebApplicationContext;

import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class MainControllerTest {

    private final String url = "/api/items";

    @Autowired
    private WebApplicationContext webApplicationContext;

    @BeforeEach
    void setUp(){
        RestAssuredMockMvc.webAppContextSetup(webApplicationContext);
    }
    @AfterEach
    void cleanUp() {
        RestAssuredMockMvc.reset();
    }

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