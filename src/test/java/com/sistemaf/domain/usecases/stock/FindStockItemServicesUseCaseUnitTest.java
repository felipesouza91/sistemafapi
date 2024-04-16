package com.sistemaf.domain.usecases.stock;

import com.sistemaf.domain.filter.StockItemFilter;
import com.sistemaf.domain.model.StockItem;
import com.sistemaf.domain.repository.estoque.stockitem.StockItemRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;

import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class FindStockItemServicesUseCaseUnitTest {

    @Spy
    private StockItemRepository repository;

    @InjectMocks
    private FindStockItemServicesUseCase findStockItemServicesUseCase;

    @Test
    @DisplayName("should call repository with correct values")
    public void given_whenFind_thenCallWithCorrectItem() {
        StockItemFilter stockItemFilter = new StockItemFilter();
        findStockItemServicesUseCase.perform(stockItemFilter, null);
        verify(repository, times(1)).findWithFilter(stockItemFilter, null);
    }

    @Test
    @DisplayName("should return a value")
    public void given_whenFind_thenReturnValues() {
        StockItemFilter stockItemFilter = new StockItemFilter();
        given(repository.findWithFilter(any(),any())).willReturn(new PageImpl<>(Collections.emptyList()));
        Page<StockItem> stockItems = findStockItemServicesUseCase.perform(stockItemFilter, null);
        assertNotNull(stockItems.getContent());
    }
}