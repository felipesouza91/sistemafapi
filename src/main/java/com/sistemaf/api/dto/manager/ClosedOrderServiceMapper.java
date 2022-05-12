package com.sistemaf.api.dto.manager;

import com.sistemaf.api.dto.input.ClosedOrderInput;
import com.sistemaf.api.dto.model.ClosedOrderDTO;
import com.sistemaf.domain.model.FechamentoOs;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;


@Mapper
public interface ClosedOrderServiceMapper {

  ClosedOrderServiceMapper INSTANCE = Mappers.getMapper(ClosedOrderServiceMapper.class);

  List<ClosedOrderDTO> mapDto(List<FechamentoOs> content);

  ClosedOrderDTO toDTO(FechamentoOs fechamentoOs);

  FechamentoOs toDomain(ClosedOrderInput input);
}
