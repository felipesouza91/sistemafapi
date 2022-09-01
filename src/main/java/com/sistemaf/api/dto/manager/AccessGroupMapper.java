package com.sistemaf.api.dto.manager;

import com.sistemaf.api.dto.input.AccessGroupInput;
import com.sistemaf.api.dto.model.AccessGroupModel;
import com.sistemaf.domain.model.GrupoAcesso;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;


@Mapper
public interface AccessGroupMapper {

  AccessGroupMapper INSTANCE = Mappers.getMapper(AccessGroupMapper.class);

  List<AccessGroupModel> mapToDTO(List<GrupoAcesso> content);

  AccessGroupModel toDTO(GrupoAcesso cidade);

  GrupoAcesso toModel(AccessGroupInput input);
}
