package com.sistemaf.api.dto.manager;

import com.sistemaf.api.dto.input.NeighborhoodInput;
import com.sistemaf.api.dto.input.ServiceOrderInput;
import com.sistemaf.api.dto.model.NeighborhoodDTO;
import com.sistemaf.api.dto.model.ServiceOrderModel;
import com.sistemaf.domain.model.Bairro;
import com.sistemaf.domain.model.OrdemServico;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper
public interface ServiceOrderMapper {

  ServiceOrderMapper INSTANCE = Mappers.getMapper(ServiceOrderMapper.class);

  List<ServiceOrderModel> mapToDTO(List<OrdemServico> content);

  ServiceOrderModel toDto(OrdemServico order);

  OrdemServico toModel(ServiceOrderInput inputBody);
}
