package com.sistemaf.api.resource.stock;

import com.sistemaf.api.docs.controllers.StockItemResourceOpenApi;
import com.sistemaf.api.dto.input.StockItemInput;
import com.sistemaf.api.dto.manager.StockItemMapper;
import com.sistemaf.api.dto.model.StockItemDTO;
import com.sistemaf.api.dto.model.StockitemResumeDTO;
import com.sistemaf.domain.contracts.stock.AddStockItemService;
import com.sistemaf.domain.contracts.stock.FindStockItemByIdService;
import com.sistemaf.domain.contracts.stock.FindStockItemServices;
import com.sistemaf.domain.contracts.stock.UpdateStockItemService;
import com.sistemaf.domain.filter.StockItemFilter;
import com.sistemaf.domain.model.StockItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.UUID;


@RestController
@RequestMapping("/stock/items")
public class StockItemResource implements StockItemResourceOpenApi {

    private StockItemMapper stockItemMapper  = StockItemMapper.INSTANCE;

    @Autowired
    private AddStockItemService addStockItemService;

    @Autowired
    private FindStockItemServices findStockItemServices;

    @Autowired
    private FindStockItemByIdService findStockItemByIdService;

    @Autowired
    private UpdateStockItemService updateStockItemService;

    @Autowired
    private ApplicationEventPublisher publisher;
    @PostMapping
    public ResponseEntity<StockItemDTO> createNewStockItem(@Valid  @RequestBody StockItemInput stockItemInput, HttpServletResponse response) {
       StockItem stockItem =  addStockItemService.perform(stockItemMapper.toModel(stockItemInput));
        return ResponseEntity.status(HttpStatus.CREATED).body(stockItemMapper.toDTO(stockItem));
    }


    @Override
    @GetMapping(params = "resume")
    public ResponseEntity<Page<StockitemResumeDTO>> findStockItemsResume(StockItemFilter stockItemFilter, Pageable pageable) {
        Page<StockItem> result = this.findStockItemServices.perform(stockItemFilter, pageable);
        Page<StockitemResumeDTO> resumeList = new PageImpl<>( this.stockItemMapper.toResumeDtoList(result.getContent()), pageable, result.getTotalElements());
        return ResponseEntity.ok(resumeList);
    }


    @Override
    @GetMapping("/{id}")
    public ResponseEntity<StockItemDTO> findStockItemById(@PathVariable UUID id) {
       StockItem stockItem =  this.findStockItemByIdService.perform(id);
        return ResponseEntity.ok(stockItemMapper.toDTO(stockItem));
    }


    @Override
    @PutMapping("/{id}")
    public ResponseEntity<StockItemDTO> updateStockItem(@PathVariable  UUID id, @Valid @RequestBody StockItemInput stockItemInput) {
        this.updateStockItemService.perform(id, stockItemMapper.toModel(stockItemInput));
        return null;
    }


}
