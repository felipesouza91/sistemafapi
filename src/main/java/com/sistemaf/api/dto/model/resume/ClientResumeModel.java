package com.sistemaf.api.dto.model.resume;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Schema(name = "Client Resume Model")
public class ClientResumeModel {

    @Schema(description = "Client code", example = "1")
    private Long id;

    @Schema(description = "Client Service code", example = "1123")
    private Integer codigoService;

    @Schema(description = "Client partition code", example = "001")
    private String codigoParticao;

    @Schema(description = "Social Name", example = "Any social name")
    private String razaoSocial;

    @Schema(description = "Company Name", example = "Any company name")
    private String fantazia;

}
