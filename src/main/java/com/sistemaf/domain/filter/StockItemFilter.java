package com.sistemaf.domain.filter;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Schema(name = "Stock Item Filter")
public class StockItemFilter {

    private String serial;

    private Boolean active;

    private Long productId;

}
