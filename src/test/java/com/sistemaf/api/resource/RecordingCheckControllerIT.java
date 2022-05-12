package com.sistemaf.api.resource;

import com.sistemaf.api.dto.input.RecordingCheckInput;
import com.sistemaf.api.dto.input.id.DvrIdInput;
import com.sistemaf.api.dto.model.RecordingCheckModel;
import com.sistemaf.util.BasicTestIntegration;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;

public class RecordingCheckControllerIT extends BasicTestIntegration {
    @Before
    public void setup() {
        accessToken = getAccessToken();
    }

    @Test
    public void shouldReturn200_whenRecordingCheck_onGetAll() {
        RestAssured.given()
                .basePath("/verificacoes")
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
    public void shouldReturn200_whenRecordingCheckIdIsValid_onGetById() {
        RestAssured.given()
                .basePath("/verificacoes")
                .accept(ContentType.JSON)
                .contentType(ContentType.JSON)
                .auth().preemptive().oauth2(super.accessToken)
                .when()
                .get("/{id}", 1)
                .then()
                .contentType(ContentType.JSON)
                .statusCode(200)
                .body("id", is(1))
                .body("dataTeste", notNullValue())
                .body("dvr", notNullValue());
    }

    @Test
    public void shouldReturn404_whenRecordingCheckIdNotExists_onGetById() {
        RestAssured.given()
                .basePath("/verificacoes")
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
    public void shouldReturn400_whenRecordingCheckIdNotValid_onGetById() {
        RestAssured.given()
                .basePath("/verificacoes")
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
    public void shouldReturn201_whenRecordingCheckFieldIsValid_onPost() {
        DvrIdInput dvrId = new DvrIdInput();
        dvrId.setId(1L);
        RecordingCheckInput body = new RecordingCheckInput();
        body.setDvr(dvrId);
        body.setHd("100GB");
        body.setQtdGravacao(100);
        body.setStatus("ONLINE");
        RestAssured.given()
                .basePath("/verificacoes")
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
                .body("dataTeste", notNullValue())
                .body("dvr.id", is(1))
                .body("usuario", notNullValue());

    }

    @Test
    public void shouldReturn400_whenRecordingCheckFieldIsNull_onPost() {
        RecordingCheckInput body = new RecordingCheckInput();
        RestAssured.given()
                .basePath("/verificacoes")
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
    public void shouldReturn400_whenRecordingCheckFieldIsSort_onPost() {
        DvrIdInput dvrId = new DvrIdInput();
        dvrId.setId(1L);
        RecordingCheckInput body = new RecordingCheckInput();
        body.setDvr(dvrId);
        body.setHd("100GB");
        body.setQtdGravacao(100);
        body.setStatus("ONL");
        RestAssured.given()
                .basePath("/verificacoes")
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
    public void shouldReturn400_whenRecordingCheckDvrIdNotExists_onPost() {
        DvrIdInput dvrId = new DvrIdInput();
        dvrId.setId(888L);
        RecordingCheckInput body = new RecordingCheckInput();
        body.setDvr(dvrId);
        body.setHd("100GB");
        body.setQtdGravacao(100);
        body.setStatus("ONLINE");
        RestAssured.given()
                .basePath("/verificacoes")
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
    public void shouldReturn404_whenRecordingCheckIdNotExists_onPut() {
        DvrIdInput dvrId = new DvrIdInput();
        dvrId.setId(1L);
        RecordingCheckInput body = new RecordingCheckInput();
        body.setDvr(dvrId);
        body.setHd("100GB");
        body.setQtdGravacao(100);
        body.setStatus("ONLINE");
        RestAssured.given()
                .basePath("/verificacoes")
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
    public void shouldReturn400_whenRecordingCheckFieldIsSort_onPut() {
        DvrIdInput dvrId = new DvrIdInput();
        dvrId.setId(1L);
        RecordingCheckInput body = new RecordingCheckInput();
        body.setDvr(dvrId);
        body.setHd("10");
        body.setQtdGravacao(100);
        body.setStatus("ONLINE");
        RestAssured.given()
                .basePath("/verificacoes")
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
    public void shouldReturn400_whenRecordingCheckFieldsInvalid_onPut() {
        RecordingCheckInput body = new RecordingCheckInput();
        RestAssured.given()
                .basePath("/verificacoes")
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
    public void shouldReturn200_whenRecordingCheckFieldIsValid_onPut() {
        DvrIdInput dvrId = new DvrIdInput();
        dvrId.setId(1L);
        RecordingCheckInput body = new RecordingCheckInput();
        body.setDvr(dvrId);
        body.setHd("100GB");
        body.setQtdGravacao(100);
        body.setStatus("OFFLINE");
        RestAssured.given()
                .basePath("/verificacoes")
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
                .body("dataTeste", notNullValue())
                .body("dvr.id", is(1))
                .body("status", is("OFFLINE"));
    }

    @Test
    public void shouldReturn400_whenRecordingCheckIdIsInvalid_onDelete() {
        RestAssured.given()
                .basePath("/verificacoes")
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
    public void shouldReturn404_whenRecordingCheckIdNotExists_onDelete() {
        RestAssured.given()
                .basePath("/verificacoes")
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
    public void shouldReturn204_whenRecordingCheckIdExists_onDelete() {
        DvrIdInput dvrId = new DvrIdInput();
        dvrId.setId(1L);
        RecordingCheckInput body = new RecordingCheckInput();
        body.setDvr(dvrId);
        body.setHd("100GB");
        body.setQtdGravacao(100);
        body.setStatus("ONLINE");
        RecordingCheckModel RecordingCheck = RestAssured.given()
                .basePath("/verificacoes")
                .accept(ContentType.JSON)
                .contentType(ContentType.JSON)
                .body(body)
                .auth().preemptive().oauth2(super.accessToken)
                .when()
                .post().as(RecordingCheckModel.class);
        RestAssured.given()
                .basePath("/verificacoes")
                .accept(ContentType.JSON)
                .contentType(ContentType.JSON)
                .auth().preemptive().oauth2(super.accessToken)
                .when()
                .delete("/{id}", RecordingCheck.getId())
                .then()
                .statusCode(204);
    }
}
