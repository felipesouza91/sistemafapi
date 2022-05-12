package com.sistemaf.api.dto.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@ApiModel("Client Model")
public class ClientModel {

    @ApiModelProperty(value = "Client code", example = "1")
    private Long id;

    @ApiModelProperty(value = "Client Service code", example = "1123")
    private Integer codigoService;

    @ApiModelProperty(value = "Client partition code", example = "001")
    private String codigoParticao;

    @ApiModelProperty(value = "Social Name", example = "Any social name")
    private String razaoSocial;

    @ApiModelProperty(value = "Company Name", example = "Any company name")
    private String fantazia;

    @ApiModelProperty(value = "Telephone number", example = "1234-1234")
    private String telefone1;

    @ApiModelProperty(value = "Telephone number", example = "1234-1234")
    private String telefone2;

    @ApiModelProperty(value = "Client Domain", example = "any.domain.com")
    private String dominio;

    private AddressModel endereco;

    private GroupModel grupo;

    @ApiModelProperty(value = "Client", example = "true")
    private Boolean ativo;

    private List<ClientInfoModel> informacoes ;

}
