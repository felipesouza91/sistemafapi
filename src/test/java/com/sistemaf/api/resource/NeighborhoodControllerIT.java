package com.sistemaf.api.resource;

import com.sistemaf.api.dto.input.NeighborhoodInput;
import com.sistemaf.api.dto.input.id.CityInputId;
import com.sistemaf.util.BasicTestIntegration;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.Before;
import org.junit.Test;
import static org.hamcrest.Matchers.*;

public class NeighborhoodControllerIT  extends BasicTestIntegration {

    @Before
    public void setup() {
        accessToken = getAccessToken();
    }

    @Test
    public void shouldReturn200_whenNeighborhoodIdIsValid_onGetAll() {
        RestAssured.given()
                .basePath("/bairros")
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
    public void shouldReturn200_whenNeighborhoodIdIsValid_onGetById() {
        RestAssured.given()
                .basePath("/bairros")
                .accept(ContentType.JSON)
                .contentType(ContentType.JSON)
                .auth().preemptive().oauth2(super.accessToken)
                .when()
                .get("/{idClient}", "1")
                .then()
                .contentType(ContentType.JSON)
                .statusCode(200)
                .body("id", is(1))
                .body("nome", notNullValue());
    }

    @Test
    public void shouldReturn404_whenNeighborhoodIdNotExists_onGetById() {
        RestAssured.given()
                .basePath("/bairros")
                .accept(ContentType.JSON)
                .contentType(ContentType.JSON)
                .auth().preemptive().oauth2(super.accessToken)
                .when()
                .get("/{idClient}", "858585")
                .then()
                .contentType(ContentType.JSON)
                .statusCode(404)
                .body("detail", notNullValue());
    }

    @Test
    public void shouldReturn400_whenNeighborhoodIdNotValid_onGetById() {
        RestAssured.given()
                .basePath("/bairros")
                .accept(ContentType.JSON)
                .contentType(ContentType.JSON)
                .auth().preemptive().oauth2(super.accessToken)
                .when()
                .get("/{idClient}", "aaaa")
                .then()
                .contentType(ContentType.JSON)
                .statusCode(400)
                .body("detail", notNullValue());
    }


    @Test
    public void shouldReturn201_whenNeighborhoodFieldIsValid_onPost() {
        CityInputId cityId = new CityInputId();
        cityId.setId(1L);
        NeighborhoodInput body = new NeighborhoodInput();
        body.setNome("New Neighborhood");
        body.setCidade(cityId);
        RestAssured.given()
                .basePath("/bairros")
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
                .body("nome", is("New Neighborhood"))
                .body("cidade.id", is(1));
    }

    @Test
    public void shouldReturn400_whenNeighborhoodFieldIsNull_onPost() {
        NeighborhoodInput body = new NeighborhoodInput();
        RestAssured.given()
                .basePath("/bairros")
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
    public void shouldReturn400_whenNeighborhoodFieldCityIdIsInvalid_onPost() {
        CityInputId cityId = new CityInputId();
        cityId.setId(1555L);
        NeighborhoodInput body = new NeighborhoodInput();
        body.setNome("New Neighborhood");
        body.setCidade(cityId);
        RestAssured.given()
                .basePath("/bairros")
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
    public void shouldReturn404_whenNeighborhoodIdNotExists_onPut() {
        CityInputId cityId = new CityInputId();
        cityId.setId(1L);
        NeighborhoodInput body = new NeighborhoodInput();
        body.setNome("New Neighborhood");
        body.setCidade(cityId);
        RestAssured.given()
                .basePath("/bairros")
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
    public void shouldReturn400_whenNeighborhoodFieldsInvalid_onPut() {
        NeighborhoodInput body = new NeighborhoodInput();
        RestAssured.given()
                .basePath("/bairros")
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
    public void shouldReturn400_whenNeighborhoodFieldCityIsInvalid_onPut() {
        CityInputId cityId = new CityInputId();
        cityId.setId(1555L);
        NeighborhoodInput body = new NeighborhoodInput();
        body.setNome("New Neighborhood");
        body.setCidade(cityId);
        RestAssured.given()
                .basePath("/bairros")
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
    public void shouldReturn200_whenNeighborhoodFieldIsValid_onPut() {
        CityInputId cityId = new CityInputId();
        cityId.setId(1L);
        NeighborhoodInput body = new NeighborhoodInput();
        body.setNome("Update Neighborhood");
        body.setCidade(cityId);
        RestAssured.given()
                .basePath("/bairros")
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
                .body("nome", is("Update Neighborhood"));
    }

    @Test
    public void shouldReturn400_whenNeighborhoodIdIsValid_onDelete() {
        RestAssured.given()
                .basePath("/bairros")
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
    public void shouldReturn404_whenNeighborhoodIdNotExists_onDelete() {
        RestAssured.given()
                .basePath("/bairros")
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
    public void shouldReturn204_whenNeighborhoodIdExists_onDelete() {
        RestAssured.given()
                .basePath("/bairros")
                .accept(ContentType.JSON)
                .contentType(ContentType.JSON)
                .auth().preemptive().oauth2(super.accessToken)
                .when()
                .delete("/{id}", 2)
                .then()
                .statusCode(204);
    }
}
