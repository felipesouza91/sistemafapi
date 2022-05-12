package com.sistemaf.api.dto.manager;

import com.sistemaf.api.dto.input.CidadeInput;
import com.sistemaf.api.dto.model.CityDTO;
import com.sistemaf.domain.model.Cidade;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface CityMapper {

  CityMapper INSTANCE = Mappers.getMapper(CityMapper.class);

  List<CityDTO> mapToDTO(List<Cidade> content);

  CityDTO toDTO(Cidade cidade);

  Cidade toModel(CidadeInput input);
}
