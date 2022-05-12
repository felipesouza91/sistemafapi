package com.sistemaf.api.dto.manager;

import com.sistemaf.api.dto.input.ServiceOrderInput;
import com.sistemaf.api.dto.input.ServiceOrderReasonInput;
import com.sistemaf.api.dto.model.ServiceOrderModel;
import com.sistemaf.api.dto.model.ServiceOrderReasonModel;
import com.sistemaf.domain.model.MotivoOs;
import com.sistemaf.domain.model.OrdemServico;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper
public interface ServiceOrderReasonMapper {

  ServiceOrderReasonMapper INSTANCE = Mappers.getMapper(ServiceOrderReasonMapper.class);

  List<ServiceOrderReasonModel> toCollectionModel(List<MotivoOs> content);

  ServiceOrderReasonModel toDto(MotivoOs motivoOs);

  MotivoOs toModel(ServiceOrderReasonInput inputBody);
}
