package com.sistemaf.api.dto.model;


import com.sistemaf.api.dto.model.resume.UserResumeModel;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import java.time.OffsetDateTime;

@Getter
@Setter
@Schema(name = "Client Info Model")
public class ClientInfoModel {

    @Schema(description = "Codigo da informação" , example = "1")
    private Long id;

    @Schema(description = "Data da criação" , example = "2020-05-05T12:30:00")
    private OffsetDateTime creationDate;

    @Schema(description = "Descrição da informação" , example = "descricao")
    private String descricao;

    @Schema(description = "Codigo da cliente" , example = "1")
    private Long clienteId;

    private UserResumeModel createdBy;

}
