package com.sistemaf.api.dto.input;

import com.sistemaf.api.dto.input.id.DvrIdInput;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter
@ApiModel("Recording Check Input")
public class RecordingCheckInput {

    @NotNull
    @NotBlank
    @Size(min=6, max=8)
    @ApiModelProperty(value = "Status", example = "ONLINE")
    private String status;

    @NotNull
    @NotBlank
    @Size(min=3 , max= 7)
    @ApiModelProperty(value = "HD Total Storage", example = "500GB")
    private String hd;

    @NotNull
    @Column(name="qtd_gravacao")
    @ApiModelProperty(value = "Recording day total", example = "100")
    private int qtdGravacao;

    @Valid
    @NotNull
    private DvrIdInput dvr;
}
