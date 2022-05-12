package com.sistemaf.api.dto.input;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.sistemaf.api.dto.input.id.ServiceOrderInputId;
import com.sistemaf.domain.model.OrdemServico;
import io.swagger.annotations.ApiModel;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

@Getter
@Setter
@ApiModel("Closed Order Input")
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
