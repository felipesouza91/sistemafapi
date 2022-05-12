package com.sistemaf.api.resource;


import com.sistemaf.api.dto.input.ServiceOrderReasonInput;
import com.sistemaf.api.dto.model.ServiceOrderReasonModel;
import com.sistemaf.util.BasicTestIntegration;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;

public class ServiceOrderReasonControllerIT extends BasicTestIntegration {

    @Before
    public void setup() {
        accessToken = getAccessToken();
    }

    @Test
    public void shouldReturn200_whenServiceOrderReasonIdIsValid_onGetAll() {
        RestAssured.given()
                .basePath("/motivososs")
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
    public void shouldReturn200_whenServiceOrderReasonIdIsValid_onGetById() {
        RestAssured.given()
                .basePath("/motivososs")
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
    public void shouldReturn404_whenServiceOrderReasonIdNotExists_onGetById() {
        RestAssured.given()
                .basePath("/motivososs")
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
    public void shouldReturn400_whenServiceOrderReasonIdNotValid_onGetById() {
        RestAssured.given()
                .basePath("/motivososs")
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
    public void shouldReturn201_whenServiceOrderReasonFieldIsValid_onPost() {
        ServiceOrderReasonInput body = new ServiceOrderReasonInput();
        body.setDescricao("New ServiceOrderReason");
        RestAssured.given()
                .basePath("/motivososs")
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
                .body("descricao", is("New ServiceOrderReason"));

    }

    @Test
    public void shouldReturn400_whenServiceOrderReasonFieldIsNull_onPost() {
        ServiceOrderReasonInput body = new ServiceOrderReasonInput();
        RestAssured.given()
                .basePath("/motivososs")
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
    public void shouldReturn400_whenServiceOrderReasonFieldIsSort_onPost() {
        ServiceOrderReasonInput body = new ServiceOrderReasonInput();
        body.setDescricao("12");
        RestAssured.given()
                .basePath("/motivososs")
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
    public void shouldReturn404_whenServiceOrderReasonIdNotExists_onPut() {
        ServiceOrderReasonInput body = new ServiceOrderReasonInput();
        body.setDescricao("Updated ServiceOrderReason");
        RestAssured.given()
                .basePath("/motivososs")
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
    public void shouldReturn400_whenServiceOrderReasonFieldIsSort_onPut() {
        ServiceOrderReasonInput body = new ServiceOrderReasonInput();
        body.setDescricao("12");
        RestAssured.given()
                .basePath("/motivososs")
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
    public void shouldReturn400_whenServiceOrderReasonFieldsInvalid_onPut() {
        ServiceOrderReasonInput body = new ServiceOrderReasonInput();
        RestAssured.given()
                .basePath("/motivososs")
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
    public void shouldReturn200_whenServiceOrderReasonFieldIsValid_onPut() {
        ServiceOrderReasonInput body = new ServiceOrderReasonInput();
        body.setDescricao("Updated ServiceOrderReason");
        RestAssured.given()
                .basePath("/motivososs")
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
                .body("descricao", is("Updated ServiceOrderReason"));
    }

    @Test
    public void shouldReturn400_whenServiceOrderReasonIdIsInvalid_onDelete() {
        RestAssured.given()
                .basePath("/motivososs")
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
    public void shouldReturn404_whenServiceOrderReasonIdNotExists_onDelete() {
        RestAssured.given()
                .basePath("/motivososs")
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
    public void shouldReturn204_whenServiceOrderReasonIdExists_onDelete() {
        ServiceOrderReasonInput body = new ServiceOrderReasonInput();
        body.setDescricao("New ServiceOrderReason");
        ServiceOrderReasonModel ServiceOrderReasonModel = RestAssured.given()
                .basePath("/motivososs")
                .accept(ContentType.JSON)
                .contentType(ContentType.JSON)
                .body(body)
                .auth().preemptive().oauth2(super.accessToken)
                .when()
                .post().as(ServiceOrderReasonModel.class);
        RestAssured.given()
                .basePath("/motivososs")
                .accept(ContentType.JSON)
                .contentType(ContentType.JSON)
                .auth().preemptive().oauth2(super.accessToken)
                .when()
                .delete("/{id}", ServiceOrderReasonModel.getId())
                .then()
                .statusCode(204);
    }
}
