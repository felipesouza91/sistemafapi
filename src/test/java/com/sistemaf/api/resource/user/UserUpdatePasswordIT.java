package com.sistemaf.api.resource.user;

import static org.hamcrest.CoreMatchers.containsString;

import com.sistemaf.domain.repository.security.grupoacesso.GrupoAcessoRepository;
import com.sistemaf.domain.repository.security.usuario.UsuarioRepository;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;

import com.sistemaf.api.dto.input.UpdatePasswordInput;
import com.sistemaf.util.BasicTestIntegration;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.springframework.security.crypto.password.PasswordEncoder;


public class UserUpdatePasswordIT extends BasicTestIntegration {

	@Autowired
	private UsuarioRepository userRepository;

	@Autowired
	private GrupoAcessoRepository grupoAcessoRepository;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Before
	public void setup() {

		accessToken = getAccessToken();
	}

	@Test
	public void shouldReturn204_whenUpdatePasswordValuesIsValid() {
		UpdatePasswordInput body = new UpdatePasswordInput();
		body.setOldPassword("admin");
		body.setNewPassword("newPassword");
		body.setConfirmationNewPassword("newPassword");
		RestAssured.given()
			.basePath("/profile")
			.accept(ContentType.JSON)
			.contentType(ContentType.JSON)
			.auth().preemptive().oauth2(super.accessToken)
			.body(body)
		.when()
			.put("/password")
		.then()
			.statusCode(HttpStatus.NO_CONTENT.value());
	}
	
	@Test
	public void shouldReturn400_whenOldPasswordIsInvalid() {
		UpdatePasswordInput body = new UpdatePasswordInput();
		body.setOldPassword("any_erro_password");
		body.setNewPassword("newPassword");
		body.setConfirmationNewPassword("newPassword");
		RestAssured.given()
			.basePath("/profile")
			.accept(ContentType.JSON)
			.contentType(ContentType.JSON)
			.auth().preemptive().oauth2(super.accessToken)
			.body(body)
		.when()
			.put("/password")
		.then()
			.statusCode(HttpStatus.BAD_REQUEST.value())
			.assertThat().body(containsString("Erro ao alterar a senha, senha atual não confere"));
	}
	
	@Test
	public void shouldReturn400_whenConfirmationNewPasswordIsInvalid() {
		UpdatePasswordInput body = new UpdatePasswordInput();
		body.setOldPassword("admin");
		body.setNewPassword("newPassword");
		body.setConfirmationNewPassword("invalid_confirmation");
		RestAssured.given()
			.basePath("/profile")
			.accept(ContentType.JSON)
			.contentType(ContentType.JSON)
			.auth().preemptive().oauth2(super.accessToken)
			.body(body)
		.when()
			.put("/password")
		.then()
			.statusCode(HttpStatus.BAD_REQUEST.value())
			.assertThat().body(containsString("Erro ao alterar a senha nova senha/confirmação invalidas"));
	}

}
