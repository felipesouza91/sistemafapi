package com.sistemaf.api.dto.manager;

import com.sistemaf.api.dto.input.ManufacturerInput;
import com.sistemaf.api.dto.model.ManufacturerDTO;
import com.sistemaf.domain.model.Fabricante;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;


import java.util.List;

@Mapper
public interface ManufacturerMapper {

  ManufacturerMapper INSTANCE = Mappers.getMapper(ManufacturerMapper.class);

  List<ManufacturerDTO> mapDto(List<Fabricante> content);

  ManufacturerDTO toDTO(Fabricante fab);

  Fabricante toModel(ManufacturerInput input);
}
