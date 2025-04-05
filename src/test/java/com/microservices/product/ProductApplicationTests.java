package com.microservices.product;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;

import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.containers.MongoDBContainer;

import io.restassured.RestAssured;

import static org.hamcrest.Matchers.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Testcontainers
class ProductApplicationTests {

    @Container
    static MongoDBContainer mongoDBContainer = new MongoDBContainer("mongo:4.4");

    @DynamicPropertySource
    static void setProperties(DynamicPropertyRegistry registry) {
        registry.add("spring.data.mongodb.uri", mongoDBContainer::getReplicaSetUrl);
    }

    @LocalServerPort
    private int port;

    @BeforeEach
    void setup() {
        RestAssured.baseURI = "http://localhost";
        RestAssured.port = port;
    }

    @Test
    void shouldCreatedProduct() {
        String requestBody = """
                {
                    "name": "Ryzen 7 5700x3d",
                    "description": "Processador Parrudo",
                    "price": 1000
                }
                """;
        RestAssured.given()
                .body(requestBody)
                .contentType("application/json")
                .when()        
                .post("/api/products")
                .then()
                .statusCode(201)
                .body("name", equalTo("Ryzen 7 5700x3d"))
                .body("description", equalTo("Processador Parrudo"))
                .body("price", equalTo(1000));
    }
}
