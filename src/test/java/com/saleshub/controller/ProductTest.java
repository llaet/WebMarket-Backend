package com.saleshub.controller;

import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class ProductTest {

    private final String PRODUCT_ENDPOINT = "http://localhost:8080/produtos";

    @Test
    public void perfomGETAllProductsRequest_checkFirstCategoryName_ExpectStatusCodeIs200(){

        given()
                .when()
                .get(PRODUCT_ENDPOINT)
                .then()
                .assertThat()
                .body("content[0].'name'", equalTo("Abajour"))
                .statusCode(equalTo(200));
    }
}
