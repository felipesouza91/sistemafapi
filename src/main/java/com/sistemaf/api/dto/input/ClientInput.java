package com.sistemaf.api.dto.input;

import com.sistemaf.api.dto.input.id.GroupIdInput;
import io.swagger.annotations.ApiModel;
import lombok.Getter;
import lombok.Setter;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter
@ApiModel("Client Input")
public class ClientInput {

    private Integer codigoService;

    @NotBlank
    @NotNull
    @Size(max=4)
    private String codigoParticao;

    @NotBlank
    @NotNull
    @Size(min= 10, max=255)
    private String razaoSocial;

    @NotBlank
    @NotNull
    @Size(min= 10, max=255)
    private String fantazia;

    @Size(min=8, max=12)
    private String telefone1;

    @Size(min=8, max=12)
    private String telefone2;

    @NotBlank
    @NotNull
    @Size(min=10, max=255)
    private String dominio;

    @Valid
    @NotNull
    private AddressInput endereco;

    @Valid
    @NotNull
    private GroupIdInput grupo;

    @NotNull
    private Boolean ativo;

}
