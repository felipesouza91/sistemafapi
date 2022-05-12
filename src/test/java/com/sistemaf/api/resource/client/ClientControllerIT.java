package com.sistemaf.api.resource.client;

import com.sistemaf.api.dto.input.AddressInput;
import com.sistemaf.api.dto.input.ClientInput;
import com.sistemaf.api.dto.input.id.GroupIdInput;
import com.sistemaf.api.dto.input.id.NeighborhoodIdInput;
import com.sistemaf.api.dto.model.ClientModel;
import com.sistemaf.util.BasicTestIntegration;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.Before;
import org.junit.Test;


import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;

public class ClientControllerIT extends BasicTestIntegration {

    @Before
    public void setup() {
        accessToken = getAccessToken();
    }

    @Test
    public void shouldReturn200_onGetAll() {
        RestAssured.given()
                .basePath("/clientes")
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
    public void shouldReturn200_whenClientIdIsValid_onGetById() {
        RestAssured.given()
                .basePath("/clientes")
                .accept(ContentType.JSON)
                .contentType(ContentType.JSON)
                .auth().preemptive().oauth2(super.accessToken)
                .when()
                .get("/{idClient}", "1")
                .then()
                .contentType(ContentType.JSON)
                .statusCode(200)
                .body("id", is(1));
    }

    @Test
    public void shouldReturn404_whenClientIdNotExists_onGetById() {
        RestAssured.given()
                .basePath("/clientes")
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
    public void shouldReturn400_whenClientIdNotValid_onGetById() {
        RestAssured.given()
                .basePath("/clientes")
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
    public void shouldReturn201_whenClientFieldIsValid_onPost() {
        ClientInput body = getBody();
        RestAssured.given()
                .basePath("/clientes")
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
                .body("fantazia", is("Nome da empresa"))
                .body("endereco.bairro.id", is(1));
    }

    @Test
    public void shouldReturn400_whenClientFieldIsNull_onPost() {
        ClientInput body = new ClientInput();
        RestAssured.given()
                .basePath("/clientes")
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
    public void shouldReturn400_whenClientPartitionCodeExists_onPost() {
        ClientInput body = getBody();
        body.setCodigoParticao("1234");
        RestAssured.given()
                .basePath("/clientes")
                .accept(ContentType.JSON)
                .contentType(ContentType.JSON)
                .body(body)
                .auth().preemptive().oauth2(super.accessToken)
                .when()
                .post()
                .then()
                .contentType(ContentType.JSON)
                .statusCode(400)
                .body("detail", is("Já existe cliente com partição solicitada"));
    }

    @Test
    public void shouldReturn400_whenClientFieldGroupIdIsInvalid_onPost() {
        GroupIdInput groupIdInput = new GroupIdInput();
        groupIdInput.setId(333L);
        ClientInput body = getBody();
        body.setGrupo(groupIdInput);
        RestAssured.given()
                .basePath("/clientes")
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
    public void shouldReturn400_whenClientFieldNeighborhoodIdIsInvalid_onPost() {
        NeighborhoodIdInput neighborhoodIdInput = new NeighborhoodIdInput();
        neighborhoodIdInput.setId(333L);
        ClientInput body = getBody();
        body.getEndereco().setBairro(neighborhoodIdInput);
        RestAssured.given()
                .basePath("/clientes")
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
    public void shouldReturn404_whenClientIdNotExists_onPut() {
        ClientInput body = getBody();
        RestAssured.given()
                .basePath("/clientes")
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
    public void shouldReturn400_whenClientFieldsInvalid_onPut() {
        ClientInput body = new ClientInput();
        RestAssured.given()
                .basePath("/clientes")
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
    public void shouldReturn400_whenClientFieldGroupIdIsInvalid_onPut() {
        GroupIdInput groupIdInput = new GroupIdInput();
        groupIdInput.setId(333L);
        ClientInput body = getBody();
        body.setGrupo(groupIdInput);
        RestAssured.given()
                .basePath("/clientes")
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
    public void shouldReturn400_whenClientFieldNeighborhoodIdIsInvalid_onPut() {
        NeighborhoodIdInput neighborhoodIdInput = new NeighborhoodIdInput();
        neighborhoodIdInput.setId(333L);
        ClientInput body = getBody();
        body.getEndereco().setBairro(neighborhoodIdInput);
        RestAssured.given()
                .basePath("/clientes")
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
    public void shouldReturn200_whenClientFieldIsValid_onPut() {
        ClientInput body = getBody();
        body.setCodigoParticao("1234");
        body.setFantazia("Update Client");
        RestAssured.given()
                .basePath("/clientes")
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
                .body("fantazia", is("Update Client"));
    }

    @Test
    public void shouldReturn400_whenClientIdIsValid_onDelete() {
        RestAssured.given()
                .basePath("/clientes")
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
    public void shouldReturn404_whenClientIdNotExists_onDelete() {
        RestAssured.given()
                .basePath("/clientes")
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
    public void shouldReturn204_whenClientIdExists_onDelete() {
        ClientInput body = getBody();
        ClientModel clientModel = RestAssured.given()
                .basePath("/clientes")
                .accept(ContentType.JSON)
                .contentType(ContentType.JSON)
                .body(body)
                .auth().preemptive().oauth2(super.accessToken)
                .when()
                .post().as(ClientModel.class);
        RestAssured.given()
                .basePath("/clientes")
                .accept(ContentType.JSON)
                .contentType(ContentType.JSON)
                .auth().preemptive().oauth2(super.accessToken)
                .when()
                .delete("/{id}", clientModel.getId())
                .then()
                .statusCode(204);
    }

    private ClientInput getBody() {
        NeighborhoodIdInput neighborhoodIdInput = new NeighborhoodIdInput();
        neighborhoodIdInput.setId(1L);

        AddressInput addressInput = new AddressInput();
        addressInput.setRua("Rua 1");
        addressInput.setNumero(100L);
        addressInput.setComplemento("Complement");
        addressInput.setReferencia("Any reference");
        addressInput.setBairro(neighborhoodIdInput);

        GroupIdInput groupIdInput = new GroupIdInput();
        groupIdInput.setId(1L);

        ClientInput body = new ClientInput();
        body.setAtivo(true);
        body.setCodigoParticao("4321");
        body.setCodigoService(4321);
        body.setEndereco(addressInput);
        body.setDominio("domain.com.br");
        body.setFantazia("Nome da empresa");
        body.setRazaoSocial("Nome da razão social");
        body.setGrupo(groupIdInput);
        body.setTelefone1("1234-1234");
        body.setTelefone2("4321-4321");
        return body;
    }
}
