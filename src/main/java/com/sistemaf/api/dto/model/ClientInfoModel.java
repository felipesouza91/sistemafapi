package com.sistemaf.api.dto.model;


import com.sistemaf.api.dto.model.resume.UserResumeModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import org.mapstruct.Mapping;

import java.time.LocalDateTime;

@Getter
@Setter
public class ClientInfoModel {

    @ApiModelProperty(value = "Codigo da informação" , example = "1")
    private Long id;

    @ApiModelProperty(value = "Data da criação" , example = "2020-05-05T12:30:00")
    private LocalDateTime creationDate;

    @ApiModelProperty(value = "Descrição da informação" , example = "descricao")
    private String descricao;

    @ApiModelProperty(value = "Codigo da cliente" , example = "1")

    private Long clienteId;

    private UserResumeModel createdBy;

}
