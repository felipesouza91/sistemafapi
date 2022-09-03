package com.sistemaf.api.dto.input;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.sistemaf.api.dto.input.id.ServiceOrderInputId;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

@Getter
@Setter
@Schema()
public class ClosedOrderInput {

    @NotBlank
    @NotNull
    @Size(min=10, max=255)
    private String motivoFechamento;

    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @NotNull
    private LocalDateTime dataVisita;

    @NotBlank
    @NotNull
    @Size(min=2, max=255)
    private String tecnico;

    @NotBlank
    @NotNull
    private String observcaoServico;

    @Valid
    @NotNull
    private ServiceOrderInputId os;
}
