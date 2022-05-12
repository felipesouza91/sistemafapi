package com.sistemaf.api.resource;

import com.sistemaf.api.dto.input.ServiceOrderInput;
import com.sistemaf.api.dto.input.id.ClientIdInput;
import com.sistemaf.api.dto.input.id.ServiceOrderReasonIdInput;
import com.sistemaf.api.dto.model.ServiceOrderModel;
import com.sistemaf.util.BasicTestIntegration;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;

public class ServiceOrderControllerIT extends BasicTestIntegration {

    @Before
    public void setup() {
        accessToken = getAccessToken();
    }

    @Test
    public void shouldReturn200_whenServiceOrder_onGetAll() {
        RestAssured.given()
                .basePath("/ordensservicos")
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
    public void shouldReturn200_whenServiceOrderIdIsValid_onGetById() {
        RestAssured.given()
                .basePath("/ordensservicos")
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
    public void shouldReturn404_whenServiceOrderIdNotExists_onGetById() {
        RestAssured.given()
                .basePath("/ordensservicos")
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
    public void shouldReturn400_whenServiceOrderIdNotValid_onGetById() {
        RestAssured.given()
                .basePath("/ordensservicos")
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
    public void shouldReturn201_whenServiceOrderFieldIsValid_onPost() {
        ClientIdInput clientId = new ClientIdInput();
        clientId.setId(1L);
        ServiceOrderReasonIdInput reasonId = new ServiceOrderReasonIdInput();
        reasonId.setId(1L);
        ServiceOrderInput body = new ServiceOrderInput();
        body.setMotivoOs(reasonId);
        body.setDescricao("asdadasda");
        body.setCliente(clientId);
        body.setPrioridadeOs("high");
        body.setSolicitante("asdadasd");
        RestAssured.given()
                .basePath("/ordensservicos")
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
                .body("descricao", is("asdadasda"));

    }

    @Test
    public void shouldReturn400_whenServiceOrderFieldIsNull_onPost() {
        ServiceOrderInput body = new ServiceOrderInput();
        RestAssured.given()
                .basePath("/ordensservicos")
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
    public void shouldReturn400_whenServiceOrderFieldIsSort_onPost() {
        ClientIdInput clientId = new ClientIdInput();
        clientId.setId(1L);
        ServiceOrderReasonIdInput reasonId = new ServiceOrderReasonIdInput();
        reasonId.setId(1L);
        ServiceOrderInput body = new ServiceOrderInput();
        body.setMotivoOs(reasonId);
        body.setDescricao("asdadasda");
        body.setCliente(clientId);
        body.setPrioridadeOs("55");
        body.setSolicitante("asdadasd");
        RestAssured.given()
                .basePath("/ordensservicos")
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
    public void shouldReturn400_whenServiceOrderClientIdNotExists_onPost() {
        ClientIdInput clientId = new ClientIdInput();
        clientId.setId(888L);
        ServiceOrderReasonIdInput reasonId = new ServiceOrderReasonIdInput();
        reasonId.setId(1L);
        ServiceOrderInput body = new ServiceOrderInput();
        body.setMotivoOs(reasonId);
        body.setDescricao("asdadasda");
        body.setCliente(clientId);
        body.setPrioridadeOs("55");
        body.setSolicitante("asdadasd");
        RestAssured.given()
                .basePath("/ordensservicos")
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
    public void shouldReturn400_whenServiceOrderReasonIdNotExists_onPost() {
        ClientIdInput clientId = new ClientIdInput();
        clientId.setId(1L);
        ServiceOrderReasonIdInput reasonId = new ServiceOrderReasonIdInput();
        reasonId.setId(888L);
        ServiceOrderInput body = new ServiceOrderInput();
        body.setMotivoOs(reasonId);
        body.setDescricao("asdadasda");
        body.setCliente(clientId);
        body.setPrioridadeOs("55");
        body.setSolicitante("asdadasd");
        RestAssured.given()
                .basePath("/ordensservicos")
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
    public void shouldReturn404_whenServiceOrderIdNotExists_onPut() {
        ClientIdInput clientId = new ClientIdInput();
        clientId.setId(1L);
        ServiceOrderReasonIdInput reasonId = new ServiceOrderReasonIdInput();
        reasonId.setId(1L);
        ServiceOrderInput body = new ServiceOrderInput();
        body.setMotivoOs(reasonId);
        body.setDescricao("asdadasda");
        body.setCliente(clientId);
        body.setPrioridadeOs("high");
        body.setSolicitante("asdadasd");
        RestAssured.given()
                .basePath("/ordensservicos")
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
    public void shouldReturn400_whenServiceOrderFieldIsSort_onPut() {
        ClientIdInput clientId = new ClientIdInput();
        clientId.setId(1L);
        ServiceOrderReasonIdInput reasonId = new ServiceOrderReasonIdInput();
        reasonId.setId(1L);
        ServiceOrderInput body = new ServiceOrderInput();
        body.setMotivoOs(reasonId);
        body.setDescricao("asdadasda");
        body.setCliente(clientId);
        body.setPrioridadeOs("55");
        body.setSolicitante("asdadasd");
        RestAssured.given()
                .basePath("/ordensservicos")
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
    public void shouldReturn400_whenServiceOrderFieldsInvalid_onPut() {
        ServiceOrderInput body = new ServiceOrderInput();
        RestAssured.given()
                .basePath("/ordensservicos")
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
    public void shouldReturn200_whenServiceOrderFieldIsValid_onPut() {
        ClientIdInput clientId = new ClientIdInput();
        clientId.setId(1L);
        ServiceOrderReasonIdInput reasonId = new ServiceOrderReasonIdInput();
        reasonId.setId(1L);
        ServiceOrderInput body = new ServiceOrderInput();
        body.setMotivoOs(reasonId);
        body.setDescricao("asdadasda");
        body.setCliente(clientId);
        body.setPrioridadeOs("high");
        body.setSolicitante("asdadasd");
        RestAssured.given()
                .basePath("/ordensservicos")
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
                .body("descricao", is("asdadasda"));
    }

    @Test
    public void shouldReturn400_whenServiceOrderIdIsInvalid_onDelete() {
        RestAssured.given()
                .basePath("/ordensservicos")
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
    public void shouldReturn404_whenServiceOrderIdNotExists_onDelete() {
        RestAssured.given()
                .basePath("/ordensservicos")
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
    public void shouldReturn204_whenServiceOrderIdExists_onDelete() {
        ClientIdInput clientId = new ClientIdInput();
        clientId.setId(1L);
        ServiceOrderReasonIdInput reasonId = new ServiceOrderReasonIdInput();
        reasonId.setId(1L);
        ServiceOrderInput body = new ServiceOrderInput();
        body.setMotivoOs(reasonId);
        body.setDescricao("asdadasda");
        body.setCliente(clientId);
        body.setPrioridadeOs("high");
        body.setSolicitante("asdadasd");
        ServiceOrderModel serviceOrder = RestAssured.given()
                .basePath("/ordensservicos")
                .accept(ContentType.JSON)
                .contentType(ContentType.JSON)
                .body(body)
                .auth().preemptive().oauth2(super.accessToken)
                .when()
                .post().as(ServiceOrderModel.class);
        RestAssured.given()
                .basePath("/ordensservicos")
                .accept(ContentType.JSON)
                .contentType(ContentType.JSON)
                .auth().preemptive().oauth2(super.accessToken)
                .when()
                .delete("/{id}", serviceOrder.getId())
                .then()
                .statusCode(204);
    }

}
