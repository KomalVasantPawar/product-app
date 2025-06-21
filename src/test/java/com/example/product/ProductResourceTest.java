package com.example.product;

import com.example.product.entity.Product;
import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;
@QuarkusTest
public class ProductResourceTest {

    @Test
    public void testCreateProduct() {
        Product p = new Product();
        p.name = "Test";
        p.description = "Testing Description";
        p.price = 50.0;
        p.quantity = 5;

        given()
          .contentType("application/json")
          .body(p)
          .when().post("/products")
          .then()
             .statusCode(201)
             .body("name", is("Test"));
    }
}