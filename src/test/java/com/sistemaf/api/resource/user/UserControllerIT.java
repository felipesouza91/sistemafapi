package com.sistemaf.api.resource.user;

import com.sistemaf.api.dto.input.UserInput;
import com.sistemaf.api.dto.input.id.AccessGroupIdInput;
import com.sistemaf.util.BasicTestIntegration;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;

public class UserControllerIT extends BasicTestIntegration {
    @Before
    public void setup() {
        accessToken = getAccessToken();
    }

    @Test
    public void shouldReturn200_onGetAll() {
        RestAssured.given()
                .basePath("/usuario")
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
    public void shouldReturn200_whenUserIdIsValid_onGetById() {
        RestAssured.given()
                .basePath("/usuario")
                .accept(ContentType.JSON)
                .contentType(ContentType.JSON)
                .auth().preemptive().oauth2(super.accessToken)
                .when()
                .get("/{id}", "1")
                .then()
                .contentType(ContentType.JSON)
                .statusCode(200)
                .body("id", is(1))
                .body("ativo", notNullValue())
                .body("nome", notNullValue())
                .body("apelido", notNullValue())
                .body("grupoAcesso", notNullValue());
    }

    @Test
    public void shouldReturn404_whenUserIdNotExists_onGetById() {
        RestAssured.given()
                .basePath("/usuario")
                .accept(ContentType.JSON)
                .contentType(ContentType.JSON)
                .auth().preemptive().oauth2(super.accessToken)
                .when()
                .get("/{id}", "858585")
                .then()
                .contentType(ContentType.JSON)
                .statusCode(404)
                .body("detail", notNullValue());
    }

    @Test
    public void shouldReturn400_whenUserIdNotValid_onGetById() {
        RestAssured.given()
                .basePath("/usuario")
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
    public void shouldReturn201_whenUserFieldIsValid_onPost() {
        AccessGroupIdInput accessGroupIdInput = new AccessGroupIdInput();
        accessGroupIdInput.setId(1L);
        UserInput body = new UserInput();
        body.setNome("new Name");
        body.setApelido("Teste");
        body.setAtivo(true);
        body.setGrupoAcesso(accessGroupIdInput);
        body.setSenha("anypassword");
        RestAssured.given()
                .basePath("/usuario")
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
                .body("apelido", is("Teste"))
                .body("ativo", is(true))
                .body("grupoAcesso.id", is(1));
    }

    @Test
    public void shouldReturn400_whenUserFieldIsNull_onPost() {
        UserInput body = new UserInput();
        RestAssured.given()
                .basePath("/usuario")
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
    public void shouldReturn400_whenUserFieldAccessGroupIdNotExists_onPost() {
        AccessGroupIdInput accessGroupIdInput = new AccessGroupIdInput();
        accessGroupIdInput.setId(12222L);
        UserInput body = new UserInput();
        body.setApelido("Teste");
        body.setNome("new Name");
        body.setAtivo(true);
        body.setGrupoAcesso(accessGroupIdInput);
        body.setSenha("anypassword");
        RestAssured.given()
                .basePath("/usuario")
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
    public void shouldReturn404_whenUserIdNotExists_onPut() {
        AccessGroupIdInput accessGroupIdInput = new AccessGroupIdInput();
        accessGroupIdInput.setId(1L);
        UserInput body = new UserInput();
        body.setApelido("Teste");
        body.setAtivo(true);
        body.setNome("new Name");
        body.setGrupoAcesso(accessGroupIdInput);
        body.setSenha("anypassword");
        RestAssured.given()
                .basePath("/usuario")
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
    public void shouldReturn400_whenUserFieldsInvalid_onPut() {
        UserInput body = new UserInput();
        RestAssured.given()
                .basePath("/usuario")
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
    public void shouldReturn400_whenUserFieldAccessGroupIdNotExists_onPut() {
        AccessGroupIdInput accessGroupIdInput = new AccessGroupIdInput();
        accessGroupIdInput.setId(2221L);
        UserInput body = new UserInput();
        body.setApelido("Teste");
        body.setAtivo(true);
        body.setNome("new Name");
        body.setGrupoAcesso(accessGroupIdInput);
        body.setSenha("anypassword");
        RestAssured.given()
                .basePath("/usuario")
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
    public void shouldReturn200_whenUserFieldIsValid_onPut() {
        AccessGroupIdInput accessGroupIdInput = new AccessGroupIdInput();
        accessGroupIdInput.setId(1L);
        UserInput body = new UserInput();
        body.setApelido("Teste");
        body.setNome("Update nome");
        body.setAtivo(true);
        body.setGrupoAcesso(accessGroupIdInput);
        body.setSenha("anypassword");
        RestAssured.given()
                .basePath("/usuario")
                .accept(ContentType.JSON)
                .contentType(ContentType.JSON)
                .body(body)
                .auth().preemptive().oauth2(super.accessToken)
                .when()
                .put("/{id}", 1)
                .then()
                .contentType(ContentType.JSON)
                .statusCode(200)
                .body("id", notNullValue())
                .body("apelido", is("Teste"))
                .body("nome", is("Update nome") )
                .body("ativo", is(true))
                .body("grupoAcesso.id", is(1));
    }
}
