package com.sistemaf.api.resource;

import com.sistemaf.api.dto.input.CidadeInput;
import com.sistemaf.api.dto.model.CityDTO;
import com.sistemaf.util.BasicTestIntegration;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;

public class CityControllerIT extends BasicTestIntegration {

    @Before
    public void setup() {
        accessToken = getAccessToken();
    }

    @Test
    public void shouldReturn200_whenCityIdIsValid_onGetAll() {
        RestAssured.given()
                .basePath("/cidades")
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
    public void shouldReturn200_whenCityIdIsValid_onGetById() {
        RestAssured.given()
                .basePath("/cidades")
                .accept(ContentType.JSON)
                .contentType(ContentType.JSON)
                .auth().preemptive().oauth2(super.accessToken)
                .when()
                .get("/{id}", 1)
                .then()
                .contentType(ContentType.JSON)
                .statusCode(200)
                .body("id", is(1))
                .body("nome", notNullValue());
    }

    @Test
    public void shouldReturn404_whenCityIdNotExists_onGetById() {
        RestAssured.given()
                .basePath("/cidades")
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
    public void shouldReturn400_whenCityIdNotValid_onGetById() {
        RestAssured.given()
                .basePath("/cidades")
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
    public void shouldReturn201_whenCityFieldIsValid_onPost() {
        CidadeInput body = new CidadeInput();
        body.setNome("New City");
        RestAssured.given()
                .basePath("/cidades")
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
                .body("nome", is("New City"));

    }

    @Test
    public void shouldReturn400_whenCityFieldIsNull_onPost() {
        CidadeInput body = new CidadeInput();
        RestAssured.given()
                .basePath("/cidades")
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
    public void shouldReturn404_whenCityIdNotExists_onPut() {
        CidadeInput body = new CidadeInput();
        body.setNome("Updated City");
        RestAssured.given()
                .basePath("/cidades")
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
    public void shouldReturn400_whenCityFieldsInvalid_onPut() {
        CidadeInput body = new CidadeInput();
        RestAssured.given()
                .basePath("/cidades")
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
    public void shouldReturn200_whenCityFieldIsValid_onPut() {
        CidadeInput body = new CidadeInput();
        body.setNome("Updated City");
        RestAssured.given()
                .basePath("/cidades")
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
                .body("nome", is("Updated City"));
    }

    @Test
    public void shouldReturn400_whenCityIdIsInvalid_onDelete() {
        RestAssured.given()
                .basePath("/cidades")
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
    public void shouldReturn404_whenCityIdNotExists_onDelete() {
        RestAssured.given()
                .basePath("/cidades")
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
    public void shouldReturn204_whenCityIdExists_onDelete() {
        CidadeInput body = new CidadeInput();
        body.setNome("New City");
        CityDTO cityModel = RestAssured.given()
                .basePath("/cidades")
                .accept(ContentType.JSON)
                .contentType(ContentType.JSON)
                .body(body)
                .auth().preemptive().oauth2(super.accessToken)
                .when()
                .post().as(CityDTO.class);
        RestAssured.given()
                .basePath("/cidades")
                .accept(ContentType.JSON)
                .contentType(ContentType.JSON)
                .auth().preemptive().oauth2(super.accessToken)
                .when()
                .delete("/{id}", cityModel.getId())
                .then()
                .statusCode(204);
    }

}
