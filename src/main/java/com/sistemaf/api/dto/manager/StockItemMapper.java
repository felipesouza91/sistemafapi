package com.sistemaf.api.dto.manager;

import com.sistemaf.api.dto.input.StockItemInput;
import com.sistemaf.api.dto.model.StockItemDTO;
import com.sistemaf.api.dto.model.StockitemResumeDTO;
import com.sistemaf.domain.model.StockItem;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "")
public interface StockItemMapper {

    StockItemMapper INSTANCE = Mappers.getMapper(StockItemMapper.class);

    StockItem toModel(StockItemInput input);

    @Mapping(source= "produto" , target = "product")
    StockItemDTO toDTO(StockItem stockItem);

    @Mapping(source = "produto.model", target = "productModel")
    @Mapping(source = "produto.model.fabricante.descricao", target = "manufactureName")
    List<StockitemResumeDTO> toResumeDtoList(List<StockItem> result);
}
