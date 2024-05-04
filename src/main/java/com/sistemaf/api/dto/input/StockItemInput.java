package com.sistemaf.api.dto.input;


import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Schema()
public class StockItemInput {

    @NotBlank
    @Schema(description =  "Item identifier", example = "SD0000SDD")
    private String serial;

    @NotNull
    @Schema(description =  "Product Id", example = "1")
    private Long productId;

}
