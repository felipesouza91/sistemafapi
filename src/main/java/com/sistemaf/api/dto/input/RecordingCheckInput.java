package com.sistemaf.api.dto.input;

import com.sistemaf.api.dto.input.id.DvrIdInput;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter
@Schema()
public class RecordingCheckInput {

    @NotNull
    @NotBlank
    @Size(min=6, max=8)
    @Schema(description  = "Status", example = "ONLINE")
    private String status;

    @NotNull
    @NotBlank
    @Size(min=3 , max= 7)
    @Schema(description  = "HD Total Storage", example = "500GB")
    private String hd;

    @NotNull
    @Column(name="qtd_gravacao")
    @Schema(description  = "Recording day total", example = "100")
    private int qtdGravacao;

    @Valid
    @NotNull
    private DvrIdInput dvr;
}
