package com.sistemaf.domain.usecases.stock;

import com.sistemaf.domain.filter.StockItemFilter;
import com.sistemaf.domain.repository.StockItemRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
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
}