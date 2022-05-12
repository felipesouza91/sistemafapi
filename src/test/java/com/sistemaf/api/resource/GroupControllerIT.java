package com.sistemaf.api.resource;

import com.sistemaf.api.dto.input.GroupInput;
import com.sistemaf.api.dto.model.GroupModel;
import com.sistemaf.util.BasicTestIntegration;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;

public class GroupControllerIT extends BasicTestIntegration {

        @Before
        public void setup() {
            accessToken = getAccessToken();
        }

        @Test
        public void shouldReturn200_whenGroupIdIsValid_onGetAll() {
            RestAssured.given()
                    .basePath("/grupos")
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
        public void shouldReturn200_whenGroupIdIsValid_onGetById() {
            RestAssured.given()
                    .basePath("/grupos")
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
        public void shouldReturn404_whenGroupIdNotExists_onGetById() {
            RestAssured.given()
                    .basePath("/grupos")
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
        public void shouldReturn400_whenGroupIdNotValid_onGetById() {
            RestAssured.given()
                    .basePath("/grupos")
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
        public void shouldReturn201_whenGroupFieldIsValid_onPost() {
            GroupInput body = new GroupInput();
            body.setNome("New Group");
            RestAssured.given()
                    .basePath("/grupos")
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
                    .body("nome", is("New Group"));

        }

        @Test
        public void shouldReturn400_whenGroupFieldIsNull_onPost() {
            GroupInput body = new GroupInput();
            RestAssured.given()
                    .basePath("/grupos")
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
    public void shouldReturn400_whenGroupFieldIsSort_onPost() {
        GroupInput body = new GroupInput();
        body.setNome("12");
        RestAssured.given()
                .basePath("/grupos")
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
        public void shouldReturn404_whenGroupIdNotExists_onPut() {
            GroupInput body = new GroupInput();
            body.setNome("Updated Group");
            RestAssured.given()
                    .basePath("/grupos")
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
        public void shouldReturn400_whenGroupFieldsInvalid_onPut() {
            GroupInput body = new GroupInput();
            RestAssured.given()
                    .basePath("/grupos")
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
        public void shouldReturn200_whenGroupFieldIsValid_onPut() {
            GroupInput body = new GroupInput();
            body.setNome("Updated Group");
            RestAssured.given()
                    .basePath("/grupos")
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
                    .body("nome", is("Updated Group"));
        }

        @Test
        public void shouldReturn400_whenGroupIdIsInvalid_onDelete() {
            RestAssured.given()
                    .basePath("/grupos")
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
        public void shouldReturn404_whenGroupIdNotExists_onDelete() {
            RestAssured.given()
                    .basePath("/grupos")
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
        public void shouldReturn204_whenGroupIdExists_onDelete() {
            GroupInput body = new GroupInput();
            body.setNome("New Group");
            GroupModel GroupModel = RestAssured.given()
                    .basePath("/grupos")
                    .accept(ContentType.JSON)
                    .contentType(ContentType.JSON)
                    .body(body)
                    .auth().preemptive().oauth2(super.accessToken)
                    .when()
                    .post().as(GroupModel.class);
            RestAssured.given()
                    .basePath("/grupos")
                    .accept(ContentType.JSON)
                    .contentType(ContentType.JSON)
                    .auth().preemptive().oauth2(super.accessToken)
                    .when()
                    .delete("/{id}", GroupModel.getId())
                    .then()
                    .statusCode(204);
        }
}
