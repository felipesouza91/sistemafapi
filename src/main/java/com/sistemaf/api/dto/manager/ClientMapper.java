package com.sistemaf.api.dto.manager;

import com.sistemaf.api.dto.input.ClientInput;
import com.sistemaf.api.dto.model.ClientModel;

import com.sistemaf.domain.model.Cliente;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;


@Mapper
public interface ClientMapper {
  ClientMapper INSTANCE = Mappers.getMapper(ClientMapper.class);

  ClientModel toDTO(Cliente client);

  Cliente toModel(ClientInput input);

  List<ClientModel> toCollectionDto(List<Cliente> list);
}
