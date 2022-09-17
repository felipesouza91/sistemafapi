package com.sistemaf.api.dto.manager;

import com.sistemaf.api.dto.input.InfoInputModel;
import com.sistemaf.api.dto.model.ClientInfoModel;
import com.sistemaf.domain.model.ClienteInformacao;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface ClientInfoMapper {
  ClientInfoMapper INSTANCE = Mappers.getMapper(ClientInfoMapper.class);

  List<ClientInfoModel> toCollectionModel(List<ClienteInformacao> list);

  //@Mapping( source = "cliente.id", target = "clienteId")
  ClientInfoModel toDTO(ClienteInformacao info);

  ClienteInformacao toModel(InfoInputModel info);
}
