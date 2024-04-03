package com.sistemaf.api.dto.model;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class StockitemResumeDTO {

    private UUID id;

    private String serial;

    private String active;

    private String productModel;

    private String manufactureName;


}
