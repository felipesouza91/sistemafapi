package com.sistemaf.api.resource.client;


import com.sistemaf.api.dto.input.InfoInputModel;
import com.sistemaf.util.BasicTestIntegration;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;

import static org.hamcrest.Matchers.*;
import org.junit.Before;
import org.junit.Test;


import java.util.Collections;

public class ClientInfoResourceIT  extends BasicTestIntegration {

    @Before
    public void setup() {
        accessToken = getAccessToken();
    }

    @Test
    public void shouldReturn200_whenClientIdIsValid() {
       RestAssured.given()
                .basePath("/clients")
                .accept(ContentType.JSON)
                .contentType(ContentType.JSON)
                .auth().preemptive().oauth2(super.accessToken)
                .when()
               .get("/{idClient}/info", "1")
                .then()
                   .contentType(ContentType.JSON)
                   .statusCode(200)
                   .body("content", equalTo(Collections.emptyList()));
    }

    @Test
    public void shouldReturn404_whenClientIdInvalid() {
        RestAssured
                .given()
                    .basePath("/clients")
                    .accept(ContentType.JSON)
                    .contentType(ContentType.JSON)
                    .auth().preemptive().oauth2(super.accessToken)
                    .when()
                .get("/{idClient}/info", "500")
                    .then()
                    .statusCode(404)
                    .body("detail", is("O Cliente encontrado ou não existe"));
    }

    @Test
    public void shouldReturn404_whenClientInfoIdInvalid() {
        RestAssured
                .given()
                .basePath("/clients")
                .accept(ContentType.JSON)
                .contentType(ContentType.JSON)
                .auth().preemptive().oauth2(super.accessToken)
                .when()
                .get("/{idClient}/info/{clientInfo}", "1", "1")
                .then()
                .statusCode(404)
                .body("detail", is("Informação não foi encontrada"));
    }

    @Test
    public void shouldReturn201_whenValidInfo() {
        InfoInputModel input = new InfoInputModel();
        input.setDescricao("Test");
        RestAssured
                .given()
                    .basePath("/clients")
                    .accept(ContentType.JSON)
                    .contentType(ContentType.JSON)
                    .auth().preemptive().oauth2(super.accessToken)
                    .body(input)
                .when()
                    .post("/{idClient}/info", "1", "1")
                    .then()
                    .statusCode(201)
                    .body("id", is(1))
                    .body("clienteId", is(1));
    }

    @Test
    public void shouldReturn200_whenValidClintInfoIdValid() {
        this.shouldReturn201_whenValidInfo();
        RestAssured
                .given()
                    .basePath("/clients")
                    .accept(ContentType.JSON)
                    .auth().preemptive().oauth2(super.accessToken)
                .when()
                    .get("/{idClient}/info/{idInfo}", "1", "1")
                    .then()
                .statusCode(200)
                .body("id", is(1));
    }

    @Test
    public void shouldReturn404_whenInvalidClientInfoId_onDelete() {
        RestAssured
                .given()
                    .basePath("/clients")
                    .accept(ContentType.JSON)
                    .auth().preemptive().oauth2(super.accessToken)
                .when()
                    .delete("/{idClient}/info/{idInfo}", "1", "1")
                    .then()
                    .statusCode(404)
                    .body("detail", is("Informação não foi encontrada"));
    }

    @Test
    public void shouldReturn204_whenValidClientInfoId_onDelete() {
        this.shouldReturn201_whenValidInfo();
        RestAssured
                .given()
                    .basePath("/clients")
                    .accept(ContentType.JSON)
                    .auth().preemptive().oauth2(super.accessToken)
                .when()
                    .delete("/{idClient}/info/{idInfo}", "1", "1")
                    .then()
                    .statusCode(204);
    }

    @Test
    public void shouldReturn404_whenInvalidClientId_onUpdateClintInfo() {
        InfoInputModel input = new InfoInputModel();
        input.setDescricao("Test update");
        RestAssured
                .given()
                    .basePath("/clients")
                    .accept(ContentType.JSON)
                    .contentType(ContentType.JSON)
                    .auth().preemptive().oauth2(super.accessToken)
                    .body(input)
                .when()
                    .put("/{idClient}/info/{idInfo}", "700", "1")
                    .then()
                .statusCode(404)
                .body("detail", is("O Cliente encontrado ou não existe"));
    }

    @Test
    public void shouldReturn404_whenInvalidClientInfoId_onUpdateClintInfo() {
        InfoInputModel input = new InfoInputModel();
        input.setDescricao("Test update");
        RestAssured
                .given()
                    .basePath("/clients")
                    .accept(ContentType.JSON)
                    .contentType(ContentType.JSON)
                    .auth().preemptive().oauth2(super.accessToken)
                    .body(input)
                .when()
                    .put("/{idClient}/info/{idInfo}", "1", "2")
                    .then()
                    .statusCode(404)
                    .body("detail", is("Informação não foi encontrada"));
    }

    @Test
    public void shouldReturn200_whenValidInfo_onUpdateClintInfo() {
        this.shouldReturn201_whenValidInfo();
        InfoInputModel input = new InfoInputModel();
        input.setDescricao("Test update");
        RestAssured
                .given()
                    .basePath("/clients")
                    .accept(ContentType.JSON)
                    .contentType(ContentType.JSON)
                    .auth().preemptive().oauth2(super.accessToken)
                    .body(input)
                .when()
                    .put("/{idClient}/info/{idInfo}", "1", "1")
                    .then()
                    .statusCode(200)
                    .body("id", is(1))
                    .body("descricao", is("Test update"))
                    .body("clienteId", is(1));;
    }
}
