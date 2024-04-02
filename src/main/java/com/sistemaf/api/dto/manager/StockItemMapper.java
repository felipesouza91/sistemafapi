package com.sistemaf.api.dto.manager;

import com.sistemaf.api.dto.input.StockItemInput;
import com.sistemaf.api.dto.model.StockItemDTO;
import com.sistemaf.domain.model.StockItem;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "")
public interface StockItemMapper {

    StockItemMapper INSTANCE = Mappers.getMapper(StockItemMapper.class);

    StockItem toModel(StockItemInput input);

    @Mapping(source= "produto" , target = "product")
    StockItemDTO toDTO(StockItem stockItem);
}
