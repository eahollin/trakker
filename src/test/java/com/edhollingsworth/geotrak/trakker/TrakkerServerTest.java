package com.edhollingsworth.geotrak.trakker;

import static io.restassured.RestAssured.given;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import io.quarkus.test.junit.QuarkusTest;

@QuarkusTest
public class TrakkerServerTest {

	@Disabled
    @Test
    public void testStreamEndpoint() {
        given()
          .when().get("/geotrak/stream")
          .then()
             .statusCode(200);
    }

}