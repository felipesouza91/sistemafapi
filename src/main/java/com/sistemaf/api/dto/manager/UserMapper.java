package com.sistemaf.api.dto.manager;

import com.sistemaf.api.dto.input.ServiceOrderInput;
import com.sistemaf.api.dto.input.UserInput;
import com.sistemaf.api.dto.model.ServiceOrderModel;
import com.sistemaf.api.dto.model.UserModel;
import com.sistemaf.api.resource.user.UsuarioResource;
import com.sistemaf.domain.model.OrdemServico;
import com.sistemaf.domain.model.Usuario;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper
public interface UserMapper {

  UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

  List<UserModel> toCollectionModel(List<Usuario> content);

  UserModel toDto(Usuario user);

  Usuario toModel(UserInput inputBody);
}
