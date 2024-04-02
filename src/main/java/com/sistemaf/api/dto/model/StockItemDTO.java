package com.sistemaf.api.dto.model;

import com.sistemaf.api.dto.model.resume.ProductResumeModel;
import com.sistemaf.api.dto.model.resume.UserResumeModel;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import org.mapstruct.Mapper;

import java.time.OffsetDateTime;
import java.util.UUID;

@Getter
@Setter
@Schema(name = "Stock Item Model")
public class StockItemDTO {

    @Schema(description =  "Stock Item Id", example = "1")
    private UUID id;
    @Schema(description =  "Serial ", example = "SD0000")
    private String serial;
    @Schema(description =  "Active status", example = "true")
    private  Boolean active;
    @Schema(description =  "Product Data")
    private ProductResumeModel product;
    @Schema(description =  "Created User")
    private UserResumeModel createdBy;
    @Schema(description =  "Updated User")
    private UserResumeModel updatedBy;
    @Schema(description =  "Created date", example = "1")
    private OffsetDateTime createdAt;
    @Schema(description =  "Update date", example = "1")
    private OffsetDateTime updatedAt;

}
