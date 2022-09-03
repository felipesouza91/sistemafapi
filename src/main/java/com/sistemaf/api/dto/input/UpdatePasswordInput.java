package com.sistemaf.api.dto.input;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@Schema()
public class UpdatePasswordInput {
	
	@NotBlank
	@Schema(description = "Current password",example = "currentPassword", required = true)
	private String oldPassword;
	
	@NotBlank
	@Schema(description = "New password",example = "newPassword", required = true)
	private String newPassword;
	
	@NotBlank
	@Schema(description = "Confirmation new password",example = "newPassword", required = true)
	private String confirmationNewPassword;
	
}
