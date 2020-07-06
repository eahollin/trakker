package com.edhollingsworth.geotrak.gtserver;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;

@QuarkusTest
public class GeoTrakServerTest {

    @Test
    public void testHelloEndpoint() {
        given()
          .when().get("/geotrak")
          .then()
             .statusCode(200)
             .body(is("hello"));
    }

}