package com.sistemaf.api.dto.manager;

import com.sistemaf.api.dto.input.ProductInput;
import com.sistemaf.api.dto.input.ServiceOrderInput;
import com.sistemaf.api.dto.model.ProductModel;
import com.sistemaf.api.dto.model.ServiceOrderModel;
import com.sistemaf.api.dto.model.resume.ProductResumeModel;
import com.sistemaf.domain.model.OrdemServico;
import com.sistemaf.domain.model.Produto;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Mapper
public interface ProductMapper {

  ProductMapper INSTANCE = Mappers.getMapper(ProductMapper.class);

  List<ProductResumeModel> toCollectionResumeModel(List<Produto> content);

  List<ProductModel> toCollectionModel(List<Produto> content);

  ProductModel toDto( Produto produto);

  Produto toModel(ProductInput inputBody);

}
