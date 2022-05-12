package com.sistemaf.api.resource;

import com.sistemaf.api.dto.input.AccessGroupInput;
import com.sistemaf.api.dto.input.id.PermissionInputId;
import com.sistemaf.api.dto.model.AccessGroupModel;
import com.sistemaf.util.BasicTestIntegration;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.Before;
import org.junit.Test;
import java.util.Arrays;
import java.util.Collections;

import static org.hamcrest.Matchers.*;

public class AccessGroupControllerIT extends BasicTestIntegration {

    @Before
    public void setup() {
        accessToken = getAccessToken();
    }

    @Test
    public void shouldReturn200_whenAccessGroupIdIsValid_onGetAll() {
        RestAssured.given()
                .basePath("/grupoacesso")
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
    public void shouldReturn200_whenAccessGroupIdIsValid_onGetById() {
        RestAssured.given()
                .basePath("/grupoacesso")
                .accept(ContentType.JSON)
                .contentType(ContentType.JSON)
                .auth().preemptive().oauth2(super.accessToken)
                .when()
                .get("/{id}", 1)
                .then()
                .contentType(ContentType.JSON)
                .statusCode(200)
                .body("id", is(1))
                .body("ativo", notNullValue())
                .body("permissoes", not(Collections.emptyList()));
    }

    @Test
    public void shouldReturn404_whenAccessGroupIdNotExists_onGetById() {
        RestAssured.given()
                .basePath("/grupoacesso")
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
    public void shouldReturn400_whenAccessGroupIdNotValid_onGetById() {
        RestAssured.given()
                .basePath("/grupoacesso")
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
    public void shouldReturn201_whenAccessGroupFieldIsValid_onPost() {
        PermissionInputId input = new PermissionInputId();
        input.setId(1L);
        AccessGroupInput body = new AccessGroupInput();
        body.setAtivo(true);
        body.setDescricao("Any Description");
        body.setPermissoes(Arrays.asList(input));
        RestAssured.given()
                .basePath("/grupoacesso")
                .accept(ContentType.JSON)
                .contentType(ContentType.JSON)
                .body(body)
                .auth().preemptive().oauth2(super.accessToken)
                .when()
                .post()
                .then()
                .contentType(ContentType.JSON)
                .statusCode(201)
                .body("id", is(2))
                .body("ativo", notNullValue())
                .body("permissoes", not(Collections.emptyList()));
    }

    @Test
    public void shouldReturn400_whenAccessGroupFieldIsNull_onPost() {
        AccessGroupInput body = new AccessGroupInput();
        RestAssured.given()
                .basePath("/grupoacesso")
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
    public void shouldReturn400_whenPermissionListIsNull_onPost() {
        AccessGroupInput body = new AccessGroupInput();
        body.setAtivo(true);
        body.setDescricao("Any Description");
        RestAssured.given()
                .basePath("/grupoacesso")
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
    public void shouldReturn400_whenPermissionListItemNotExist_onPost() {
        PermissionInputId input = new PermissionInputId();
        input.setId(9999L);
        AccessGroupInput body = new AccessGroupInput();
        body.setAtivo(true);
        body.setDescricao("Any Description");
        body.setPermissoes(Arrays.asList(input));
        RestAssured.given()
                .basePath("/grupoacesso")
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
    public void shouldReturn404_whenAccessGroupIdNotExists_onPut() {
        PermissionInputId input = new PermissionInputId();
        input.setId(1L);
        AccessGroupInput body = new AccessGroupInput();
        body.setAtivo(true);
        body.setDescricao("Any Description");
        body.setPermissoes(Arrays.asList(input));

        RestAssured.given()
                .basePath("/grupoacesso")
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
    public void shouldReturn400_whenAccessGroupFieldsInvalid_onPut() {
        AccessGroupInput body = new AccessGroupInput();
        RestAssured.given()
                .basePath("/grupoacesso")
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
    public void shouldReturn200_whenAccessGroupFieldIsValid_onPut() {
        PermissionInputId input = new PermissionInputId();
        input.setId(1L);
        AccessGroupInput body = new AccessGroupInput();
        body.setAtivo(true);
        body.setDescricao("Update Any Description");
        body.setPermissoes(Arrays.asList(input));
        RestAssured.given()
                .basePath("/grupoacesso")
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
                .body("descricao", is("Update Any Description"))
                .body("ativo", notNullValue())
                .body("permissoes", not(Collections.emptyList()));
    }

    @Test
    public void shouldReturn400_whenAccessGroupIdIsInvalid_onDelete() {
        RestAssured.given()
                .basePath("/grupoacesso")
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
    public void shouldReturn404_whenAccessGroupIdNotExists_onDelete() {
        RestAssured.given()
                .basePath("/grupoacesso")
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
    public void shouldReturn204_whenAccessGroupIdExists_onDelete() {
        PermissionInputId input = new PermissionInputId();
        input.setId(1L);
        AccessGroupInput body = new AccessGroupInput();
        body.setAtivo(true);
        body.setDescricao("Any Description");
        body.setPermissoes(Arrays.asList(input));
        AccessGroupModel AccessGroupModel = RestAssured.given()
                .basePath("/grupoacesso")
                .accept(ContentType.JSON)
                .contentType(ContentType.JSON)
                .body(body)
                .auth().preemptive().oauth2(super.accessToken)
                .when()
                .post().as(AccessGroupModel.class);
        RestAssured.given()
                .basePath("/grupoacesso")
                .accept(ContentType.JSON)
                .contentType(ContentType.JSON)
                .auth().preemptive().oauth2(super.accessToken)
                .when()
                .delete("/{id}", AccessGroupModel.getId())
                .then()
                .statusCode(204);
    }

}

