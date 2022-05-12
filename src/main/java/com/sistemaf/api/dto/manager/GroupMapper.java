package com.sistemaf.api.dto.manager;

import com.sistemaf.api.dto.input.AccessGroupInput;
import com.sistemaf.api.dto.input.GroupInput;
import com.sistemaf.api.dto.model.AccessGroupModel;
import com.sistemaf.api.dto.model.GroupModel;

import com.sistemaf.domain.model.Grupo;
import com.sistemaf.domain.model.GrupoAcesso;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper
public interface GroupMapper {

  GroupMapper INSTANCE = Mappers.getMapper(GroupMapper.class);

  List<GroupModel> mapToDTO(List<Grupo> content);

  GroupModel toDTO(Grupo group);

  Grupo toModel(GroupInput input);
}
