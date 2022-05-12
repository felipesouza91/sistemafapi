package com.sistemaf.api.dto.input;

import javax.validation.constraints.NotBlank;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdatePasswordInput {
	
	@NotBlank
	@ApiModelProperty(value = "Current password",example = "currentPassword", required = true)
	private String oldPassword;
	
	@NotBlank
	@ApiModelProperty(value = "New password",example = "newPassword", required = true)
	private String newPassword;
	
	@NotBlank
	@ApiModelProperty(value = "Confirmation new password",example = "newPassword", required = true)
	private String confirmationNewPassword;
	
}
