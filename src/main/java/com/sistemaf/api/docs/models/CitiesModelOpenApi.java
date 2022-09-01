package com.sistemaf.api.docs.models;

import com.sistemaf.domain.model.Cidade;
import io.swagger.v3.oas.annotations.media.Schema;

@Schema(name = "CitiesModel")
public class CitiesModelOpenApi extends PagedModelOpenApi<Cidade>{}
