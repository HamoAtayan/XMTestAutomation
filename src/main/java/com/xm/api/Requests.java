package com.xm.api;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import lombok.extern.slf4j.Slf4j;

import static io.restassured.RestAssured.given;

@Slf4j
public class Requests {

    public Response getData(String serverUrl, String endPoint) {
        RestAssured.baseURI = serverUrl;
        log.info("get role groups");
        return
                given()
                        .when()
                        .get(serverUrl + endPoint);
    }

}
