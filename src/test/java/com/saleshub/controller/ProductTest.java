package com.saleshub.controller;

import com.saleshub.domain.Product;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasItem;

public class ProductTest {

    private static final String PRODUCT_ENDPOINT = "http://localhost:8080/produtos";

    @Test
    public void performGETAll_thenPrintRequestBody_ExpectStatusCode200(){

        given()
                .when()
                .get(PRODUCT_ENDPOINT)
                .then()
                .log().body()
                .and()
                .statusCode(HttpStatus.OK.value());
    }

    @Test
    public void perfomGETAllProductsRequest_checkContentList_ExpectHasAbajour(){

        Product abajour = new Product(null,"Abajour",100.0);

        given()
                .when()
                .get(PRODUCT_ENDPOINT)
                .then()
                .assertThat()
                .body("content.name", hasItem(abajour.getName()))
                .statusCode(equalTo(200));
    }

    @Test
    public void performGETAllProductsRequest_checkContentType_ExpectApplicationJson(){

        given()
                .when()
                .get(PRODUCT_ENDPOINT)
                .then()
                .assertThat()
                .contentType(ContentType.JSON);
    }
}
