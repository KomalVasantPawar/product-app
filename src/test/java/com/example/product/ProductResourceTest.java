package com.example.product;

import com.example.product.entity.Product;
import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.*;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

@QuarkusTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class ProductResourceTest {

    static Long createdProductId;

@Test
@Order(1)
public void testCreateProduct() {
    Product p = new Product();
    p.name = "Test";
    p.description = "Testing Description";
    p.price = 50.0;
    p.quantity = 5;

    Object rawId =
        given()
            .contentType("application/json")
            .body(p)
        .when()
            .post("/products")
        .then()
            .statusCode(201)
            .log().body()
            .body("name", is("Test"))
            .extract()
            .path("id");

    createdProductId = Long.valueOf(rawId.toString());
}


    @Test
    @Order(2)
    public void testGetAllProducts() {
        when().get("/products")
        .then()
            .statusCode(200)
            .body("size()", greaterThan(0));
    }

    @Test
    @Order(3)
    public void testGetProductById() {
        when()
            .get("/products/" + createdProductId)
        .then()
            .statusCode(200)
            .body("id", is(createdProductId.intValue()));
    }

    @Test
    @Order(4)
    public void testUpdateProduct() {
        Product updated = new Product();
        updated.name = "Updated Name";
        updated.description = "Updated Description";
        updated.price = 60.0;
        updated.quantity = 10;

        given()
            .contentType("application/json")
            .body(updated)
        .when()
            .put("/products/" + createdProductId)
        .then()
            .statusCode(200)
            .body("name", is("Updated Name"));
    }
}
