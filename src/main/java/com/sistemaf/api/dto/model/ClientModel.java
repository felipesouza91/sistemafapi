package com.sistemaf.api.dto.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Schema(name = "Client Model")
public class ClientModel {

    @Schema(description =  "Client code", example = "1")
    private Long id;

    @Schema(description =  "Client Service code", example = "1123")
    private Integer codigoService;

    @Schema(description =  "Client partition code", example = "001")
    private String codigoParticao;

    @Schema(description =  "Social Name", example = "Any social name")
    private String razaoSocial;

    @Schema(description =  "Company Name", example = "Any company name")
    private String fantazia;

    @Schema(description =  "Telephone number", example = "1234-1234")
    private String telefone1;

    @Schema(description =  "Telephone number", example = "1234-1234")
    private String telefone2;

    @Schema(description =  "Client Domain", example = "any.domain.com")
    private String dominio;

    private AddressModel endereco;

    private GroupModel grupo;

    @Schema(description =  "Client", example = "true")
    private Boolean ativo;

    private List<ClientInfoModel> informacoes ;

}
