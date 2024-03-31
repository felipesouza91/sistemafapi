package com.sistemaf.api.resource.stock;

import com.sistemaf.api.dto.input.StockItemInput;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;


@RestController
@RequestMapping("/stock/items")
public class StockItemResource {


    @PostMapping
    public void createNewStockItem(@Valid  @RequestBody StockItemInput stockItemInput) {

    }

}
