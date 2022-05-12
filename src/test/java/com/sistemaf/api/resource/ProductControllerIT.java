package com.sistemaf.api.resource;

import com.sistemaf.api.dto.input.ProductInput;
import com.sistemaf.api.dto.input.id.ManufacturerInputId;
import com.sistemaf.api.dto.model.ProductModel;
import com.sistemaf.util.BasicTestIntegration;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;

public class ProductControllerIT extends BasicTestIntegration {

    @Before
    public void setup() {
        accessToken = getAccessToken();
    }

    @Test
    public void shouldReturn200_onGetAll() {
        RestAssured.given()
                .basePath("/produtos")
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
    public void shouldReturn200_whenProductIdIsValid_onGetById() {
        RestAssured.given()
                .basePath("/produtos")
                .accept(ContentType.JSON)
                .contentType(ContentType.JSON)
                .auth().preemptive().oauth2(super.accessToken)
                .when()
                .get("/{idClient}", "1")
                .then()
                .contentType(ContentType.JSON)
                .statusCode(200)
                .body("id", is(1))
                .body("modelo", notNullValue())
                .body("createdBy", notNullValue())
                .body("creationDate", notNullValue());
    }

    @Test
    public void shouldReturn404_whenProductIdNotExists_onGetById() {
        RestAssured.given()
                .basePath("/produtos")
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
    public void shouldReturn400_whenProductIdNotValid_onGetById() {
        RestAssured.given()
                .basePath("/produtos")
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
    public void shouldReturn201_whenProductFieldIsValid_onPost() {
        ManufacturerInputId manufacturerInputId = new ManufacturerInputId();
        manufacturerInputId.setId(1L);
        ProductInput body = new ProductInput();
        body.setDescricao("Any description");
        body.setModelo("new Any model");
        body.setValorUnitario(1515.00F);
        body.setFabricante(manufacturerInputId);
        RestAssured.given()
                .basePath("/produtos")
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
                .body("descricao", is("Any description"))
                .body("modelo", is("new Any model"))
                .body("valorUnitario", is(1515.00F))
                .body("createdBy", notNullValue())
                .body("creationDate", notNullValue());
    }

    @Test
    public void shouldReturn400_whenProductFieldIsNull_onPost() {
        ProductInput body = new ProductInput();
        RestAssured.given()
                .basePath("/produtos")
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
    public void shouldReturn400_whenProductFieldManufacturerIdNotExists_onPost() {
        ManufacturerInputId manufacturerInputId = new ManufacturerInputId();
        manufacturerInputId.setId(2221L);
        ProductInput body = new ProductInput();
        body.setDescricao("Any description");
        body.setModelo("new Any model");
        body.setValorUnitario(1515.00F);
        body.setFabricante(manufacturerInputId);
        RestAssured.given()
                .basePath("/produtos")
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
    public void shouldReturn404_whenProductIdNotExists_onPut() {
        ManufacturerInputId manufacturerInputId = new ManufacturerInputId();
        manufacturerInputId.setId(1L);
        ProductInput body = new ProductInput();
        body.setDescricao("Any description");
        body.setModelo("new Any model");
        body.setValorUnitario(1515.00F);
        body.setFabricante(manufacturerInputId);
        RestAssured.given()
                .basePath("/produtos")
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
    public void shouldReturn400_whenProductFieldsInvalid_onPut() {
        ProductInput body = new ProductInput();
        RestAssured.given()
                .basePath("/produtos")
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
    public void shouldReturn400_whenProductFieldManufacturerIdNotExists_onPut() {
        ManufacturerInputId manufacturerInputId = new ManufacturerInputId();
        manufacturerInputId.setId(2221L);
        ProductInput body = new ProductInput();
        body.setDescricao("Any description");
        body.setModelo("new Any model");
        body.setValorUnitario(1515.00F);
        body.setFabricante(manufacturerInputId);
        RestAssured.given()
                .basePath("/produtos")
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
    public void shouldReturn200_whenProductFieldIsValid_onPut() {
        ManufacturerInputId manufacturerInputId = new ManufacturerInputId();
        manufacturerInputId.setId(1L);
        ProductInput body = new ProductInput();
        body.setDescricao("Updated Any description");
        body.setModelo("Updated Any model");
        body.setValorUnitario(15.00F);
        body.setFabricante(manufacturerInputId);
        RestAssured.given()
                .basePath("/produtos")
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
                .body("descricao", is("Updated Any description"))
                .body("modelo", is("Updated Any model"))
                .body("valorUnitario", is(15.00F))
                .body("createdBy", notNullValue())
                .body("creationDate", notNullValue())
                .body("lastModifiedBy", notNullValue())
                .body("lastModifiedDate", notNullValue());
    }

    @Test
    public void shouldReturn400_whenProductIdIsValid_onDelete() {
        RestAssured.given()
                .basePath("/produtos")
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
    public void shouldReturn404_whenProductIdNotExists_onDelete() {
        RestAssured.given()
                .basePath("/produtos")
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
    public void shouldReturn204_whenProductIdExists_onDelete() {
        ManufacturerInputId manufacturerInputId = new ManufacturerInputId();
        manufacturerInputId.setId(1L);
        ProductInput body = new ProductInput();
        body.setDescricao("Any description");
        body.setModelo("new Any model");
        body.setValorUnitario(1515.00F);
        body.setFabricante(manufacturerInputId);
        ProductModel savedModel = RestAssured.given()
                .basePath("/produtos")
                .accept(ContentType.JSON)
                .contentType(ContentType.JSON)
                .body(body)
                .auth().preemptive().oauth2(super.accessToken)
                .when()
                .post().as(ProductModel.class);
        RestAssured.given()
                .basePath("/produtos")
                .accept(ContentType.JSON)
                .contentType(ContentType.JSON)
                .auth().preemptive().oauth2(super.accessToken)
                .when()
                .delete("/{id}", savedModel.getId())
                .then()
                .statusCode(204);
    }
}
