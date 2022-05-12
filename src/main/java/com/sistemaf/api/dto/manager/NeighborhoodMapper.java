package com.sistemaf.api.dto.manager;

import com.sistemaf.api.dto.input.NeighborhoodInput;
import com.sistemaf.api.dto.model.NeighborhoodDTO;
import com.sistemaf.domain.model.Bairro;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface NeighborhoodMapper {

  NeighborhoodMapper INSTANCE = Mappers.getMapper(NeighborhoodMapper.class);

  List<NeighborhoodDTO> toCollectionModel(List<Bairro> content);

  NeighborhoodDTO toDto(Bairro bairro);

  Bairro toModel(NeighborhoodInput inputBody);
}
