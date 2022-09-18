package com.sistemaf.api.resource;

import com.sistemaf.api.dto.input.ClosedOrderInput;
import com.sistemaf.api.dto.input.id.ServiceOrderInputId;
import com.sistemaf.api.dto.model.ClosedOrderDTO;
import com.sistemaf.util.BasicTestIntegration;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.Before;
import org.junit.Test;

import java.time.OffsetDateTime;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;

public class ClosedOrderControllerIT extends BasicTestIntegration {

    @Before
    public void setup() {
        accessToken = getAccessToken();
    }

    @Test
    public void shouldReturn200_whenClosedOrderIdIsValid_onGetAll() {
        RestAssured.given()
                .basePath("/fechamentososs")
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
    public void shouldReturn200_whenClosedOrderIdIsValid_onGetById() {
        RestAssured.given()
                .basePath("/fechamentososs")
                .accept(ContentType.JSON)
                .contentType(ContentType.JSON)
                .auth().preemptive().oauth2(super.accessToken)
                .when()
                .get("/{id}", 1)
                .then()
                .contentType(ContentType.JSON)
                .statusCode(200)
                .body("id", is(1))
                .body("dataFechamento", notNullValue());
    }

    @Test
    public void shouldReturn404_whenClosedOrderIdNotExists_onGetById() {
        RestAssured.given()
                .basePath("/fechamentososs")
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
    public void shouldReturn400_whenClosedOrderIdNotValid_onGetById() {
        RestAssured.given()
                .basePath("/fechamentososs")
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
    public void shouldReturn201_whenClosedOrderFieldIsValid_onPost() {
        ServiceOrderInputId order = new ServiceOrderInputId();
        order.setId(1L);
        ClosedOrderInput body = new ClosedOrderInput();
        body.setDataVisita(OffsetDateTime.now());
        body.setTecnico("Any technician");
        body.setMotivoFechamento("service order concluded");
        body.setObservcaoServico("observation");
        body.setOs(order);
        RestAssured.given()
                .basePath("/fechamentososs")
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
                .body("dataVisita", notNullValue())
                .body("dataFechamento", notNullValue());

    }

    @Test
    public void shouldReturn400_whenClosedOrderFieldIsNull_onPost() {
        ClosedOrderInput body = new ClosedOrderInput();
        RestAssured.given()
                .basePath("/fechamentososs")
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
    public void shouldReturn400_whenServiceOrderInputIsNull_onPost() {
        ClosedOrderInput body = new ClosedOrderInput();
        body.setDataVisita(OffsetDateTime.now());
        body.setTecnico("Any technician");
        body.setMotivoFechamento("service order concluded");
        body.setObservcaoServico("observation");
        RestAssured.given()
                .basePath("/fechamentososs")
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
    public void shouldReturn400_whenServiceOrderInputIdIsNull_onPost() {
        ServiceOrderInputId order = new ServiceOrderInputId();
        ClosedOrderInput body = new ClosedOrderInput();
        body.setDataVisita(OffsetDateTime.now());
        body.setTecnico("Any technician");
        body.setMotivoFechamento("service order concluded");
        body.setObservcaoServico("observation");
        body.setOs(order);
        RestAssured.given()
                .basePath("/fechamentososs")
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
    public void shouldReturn400_whenServiceOrderNotExist_onPost() {
        ServiceOrderInputId order = new ServiceOrderInputId();
        order.setId(8888L);
        ClosedOrderInput body = new ClosedOrderInput();
        body.setDataVisita(OffsetDateTime.now());
        body.setTecnico("Any technician");
        body.setMotivoFechamento("service order concluded");
        body.setObservcaoServico("observation");
        body.setOs(order);
        RestAssured.given()
                .basePath("/fechamentososs")
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
    public void shouldReturn404_whenClosedOrderIdNotExists_onPut() {
        ServiceOrderInputId order = new ServiceOrderInputId();
        order.setId(1L);
        ClosedOrderInput body = new ClosedOrderInput();
        body.setDataVisita(OffsetDateTime.now());
        body.setTecnico("Any technician");
        body.setMotivoFechamento("service order concluded");
        body.setObservcaoServico("observation");
        body.setOs(order);
        RestAssured.given()
                .basePath("/fechamentososs")
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
    public void shouldReturn400_whenClosedOrderFieldsInvalid_onPut() {
        ClosedOrderInput body = new ClosedOrderInput();
        RestAssured.given()
                .basePath("/fechamentososs")
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
    public void shouldReturn200_whenClosedOrderFieldIsValid_onPut() {
        ServiceOrderInputId order = new ServiceOrderInputId();
        order.setId(1L);
        ClosedOrderInput body = new ClosedOrderInput();
        body.setDataVisita(OffsetDateTime.now());
        body.setTecnico("Any technician");
        body.setMotivoFechamento("new service order concluded");
        body.setObservcaoServico("observation");
        body.setOs(order);
        RestAssured.given()
                .basePath("/fechamentososs")
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
                .body("motivoFechamento", is("new service order concluded"));
    }

    @Test
    public void shouldReturn400_whenClosedOrderIdIsInvalid_onDelete() {
        RestAssured.given()
                .basePath("/fechamentososs")
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
    public void shouldReturn404_whenClosedOrderIdNotExists_onDelete() {
        RestAssured.given()
                .basePath("/fechamentososs")
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
    public void shouldReturn204_whenClosedOrderIdExists_onDelete() {
        ServiceOrderInputId order = new ServiceOrderInputId();
        order.setId(1L);
        ClosedOrderInput body = new ClosedOrderInput();
        body.setDataVisita(OffsetDateTime.now());
        body.setTecnico("Any technician");
        body.setMotivoFechamento("service order concluded");
        body.setObservcaoServico("observation");
        body.setOs(order);
        ClosedOrderDTO closedOrderDTO = RestAssured.given()
                .basePath("/fechamentososs")
                .accept(ContentType.JSON)
                .contentType(ContentType.JSON)
                .body(body)
                .auth().preemptive().oauth2(super.accessToken)
                .when()
                .post().as(ClosedOrderDTO.class);
        RestAssured.given()
                .basePath("/fechamentososs")
                .accept(ContentType.JSON)
                .contentType(ContentType.JSON)
                .auth().preemptive().oauth2(super.accessToken)
                .when()
                .delete("/{id}", closedOrderDTO.getId())
                .then()
                .statusCode(204);
    }


}