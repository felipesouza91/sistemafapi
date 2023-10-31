package com.sistemaf.api.dto.manager;

import com.sistemaf.api.dto.input.ClientInput;
import com.sistemaf.api.dto.input.FileInput;
import com.sistemaf.api.dto.model.ClientModel;
import com.sistemaf.domain.model.Cliente;
import com.sistemaf.domain.model.definition.FileReference;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;


@Mapper
public interface FileReferenceMapper {
  FileReferenceMapper INSTANCE = Mappers.getMapper(FileReferenceMapper.class);

  FileReference toModel(FileInput input);

}
