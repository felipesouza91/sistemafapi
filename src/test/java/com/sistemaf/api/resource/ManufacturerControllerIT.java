package com.sistemaf.api.resource;


import com.sistemaf.api.dto.input.ManufacturerInput;
import com.sistemaf.api.dto.model.ManufacturerDTO;
import com.sistemaf.util.BasicTestIntegration;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;

public class ManufacturerControllerIT extends BasicTestIntegration {

    @Before
    public void setup() {
        accessToken = getAccessToken();
    }

    @Test
    public void shouldReturn200_whenManufacturerIdIsValid_onGetAll() {
        RestAssured.given()
                .basePath("/fabricantes")
                .accept(ContentType.JSON)
                .contentType(ContentType.JSON)
                .auth().preemptive().oauth2(super.accessToken)
                .when()
                .get()
                .then()
                .contentType(ContentType.JSON)
                .statusCode(200)
                .body("content", notNullValue());
    }


    @Test
    public void shouldReturn200_whenManufacturerIdIsValid_onGetById() {
        ManufacturerInput body = new ManufacturerInput();
        body.setDescricao("New Manufacturer");
        RestAssured.given()
                .basePath("/fabricantes")
                .accept(ContentType.JSON)
                .contentType(ContentType.JSON)
                .body(body)
                .auth().preemptive().oauth2(super.accessToken)
                .when()
                .post();
        RestAssured.given()
                .basePath("/fabricantes")
                .accept(ContentType.JSON)
                .contentType(ContentType.JSON)
                .auth().preemptive().oauth2(super.accessToken)
                .when()
                .get("/{id}", 1)
                .then()
                .contentType(ContentType.JSON)
                .statusCode(200)
                .body("id", is(1))
                .body("descricao", notNullValue());
    }

    @Test
    public void shouldReturn404_whenManufacturerIdNotExists_onGetById() {
        RestAssured.given()
                .basePath("/fabricantes")
                .accept(ContentType.JSON)
                .contentType(ContentType.JSON)
                .auth().preemptive().oauth2(super.accessToken)
                .when()
                .get("/{id}", 858585)
                .then()
                .contentType(ContentType.JSON)
                .statusCode(404)
                .body("detail", notNullValue());
    }

    @Test
    public void shouldReturn400_whenManufacturerIdNotValid_onGetById() {
        RestAssured.given()
                .basePath("/fabricantes")
                .accept(ContentType.JSON)
                .contentType(ContentType.JSON)
                .auth().preemptive().oauth2(super.accessToken)
                .when()
                .get("/{id}", "aaaa")
                .then()
                .contentType(ContentType.JSON)
                .statusCode(400)
                .body("detail", notNullValue());
    }


    @Test
    public void shouldReturn201_whenManufacturerFieldIsValid_onPost() {
        ManufacturerInput body = new ManufacturerInput();
        body.setDescricao("New Manufacturer");
        RestAssured.given()
                .basePath("/fabricantes")
                .accept(ContentType.JSON)
                .contentType(ContentType.JSON)
                .body(body)
                .auth().preemptive().oauth2(super.accessToken)
                .when()
                .post()
                .then()
                .contentType(ContentType.JSON)
                .statusCode(201)
                .body("id", notNullValue())
                .body("descricao", is("New Manufacturer"));

    }

    @Test
    public void shouldReturn400_whenManufacturerFieldIsNull_onPost() {
        ManufacturerInput body = new ManufacturerInput();
        RestAssured.given()
                .basePath("/fabricantes")
                .accept(ContentType.JSON)
                .contentType(ContentType.JSON)
                .body(body)
                .auth().preemptive().oauth2(super.accessToken)
                .when()
                .post()
                .then()
                .contentType(ContentType.JSON)
                .statusCode(400)
                .body("detail", notNullValue());
    }


    @Test
    public void shouldReturn404_whenManufacturerIdNotExists_onPut() {
        ManufacturerInput body = new ManufacturerInput();
        body.setDescricao("Updated Manufacturer");
        RestAssured.given()
                .basePath("/fabricantes")
                .accept(ContentType.JSON)
                .contentType(ContentType.JSON)
                .body(body)
                .auth().preemptive().oauth2(super.accessToken)
                .when()
                .put("/{id}", 5848)
                .then()
                .contentType(ContentType.JSON)
                .statusCode(404)
                .body("detail", notNullValue());
    }

    @Test
    public void shouldReturn400_whenManufacturerFieldsInvalid_onPut() {
        ManufacturerInput body = new ManufacturerInput();
        RestAssured.given()
                .basePath("/fabricantes")
                .accept(ContentType.JSON)
                .contentType(ContentType.JSON)
                .body(body)
                .auth().preemptive().oauth2(super.accessToken)
                .when()
                .put("/{id}", 1)
                .then()
                .contentType(ContentType.JSON)
                .statusCode(400)
                .body("detail", notNullValue());
    }


    @Test
    public void shouldReturn200_whenManufacturerFieldIsValid_onPut() {
        ManufacturerInput newBody = new ManufacturerInput();
        newBody.setDescricao("New Manufacturer");
        RestAssured.given()
                .basePath("/fabricantes")
                .accept(ContentType.JSON)
                .contentType(ContentType.JSON)
                .body(newBody)
                .auth().preemptive().oauth2(super.accessToken)
                .when()
                .post();
        ManufacturerInput body = new ManufacturerInput();
        body.setDescricao("Updated Manufacturer");
        RestAssured.given()
                .basePath("/fabricantes")
                .accept(ContentType.JSON)
                .contentType(ContentType.JSON)
                .body(body)
                .auth().preemptive().oauth2(super.accessToken)
                .when()
                .put("/{id}", 1)
                .then()
                .contentType(ContentType.JSON)
                .statusCode(200)
                .body("id", is(1))
                .body("descricao", is("Updated Manufacturer"));
    }

    @Test
    public void shouldReturn400_whenManufacturerIdIsInvalid_onDelete() {
        RestAssured.given()
                .basePath("/fabricantes")
                .accept(ContentType.JSON)
                .contentType(ContentType.JSON)
                .auth().preemptive().oauth2(super.accessToken)
                .when()
                .delete("/{id}", "aaaa")
                .then()
                .contentType(ContentType.JSON)
                .statusCode(400)
                .body("detail", notNullValue());
    }

    @Test
    public void shouldReturn404_whenManufacturerIdNotExists_onDelete() {
        RestAssured.given()
                .basePath("/fabricantes")
                .accept(ContentType.JSON)
                .contentType(ContentType.JSON)
                .auth().preemptive().oauth2(super.accessToken)
                .when()
                .delete("/{id}", 15858)
                .then()
                .contentType(ContentType.JSON)
                .statusCode(404)
                .body("detail", notNullValue());
    }

    @Test
    public void shouldReturn204_whenManufacturerIdExists_onDelete() {
        ManufacturerInput body = new ManufacturerInput();
        body.setDescricao("New Manufacturer");
        ManufacturerDTO ManufacturerDTO = RestAssured.given()
                .basePath("/fabricantes")
                .accept(ContentType.JSON)
                .contentType(ContentType.JSON)
                .body(body)
                .auth().preemptive().oauth2(super.accessToken)
                .when()
                .post().as(ManufacturerDTO.class);
        RestAssured.given()
                .basePath("/fabricantes")
                .accept(ContentType.JSON)
                .contentType(ContentType.JSON)
                .auth().preemptive().oauth2(super.accessToken)
                .when()
                .delete("/{id}", ManufacturerDTO.getId())
                .then()
                .statusCode(204);
    }


}
