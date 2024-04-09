package com.sistemaf.domain.usecases.stock;


import com.sistemaf.domain.model.StockItem;
import org.instancio.Instancio;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class AddStockItemUseCaseUnitTest {


    @Spy
    private AddStockItemUseCase addStockItemUseCase;

    @Test
    @DisplayName("should called with correct values")
    public void givenInputData_whenExecuteUseCase_thenCalledWithCorrectItem() {
        StockItem stockItem = Instancio.create(StockItem.class);
        stockItem.setId(null);
        addStockItemUseCase.perform(stockItem);
        verify(addStockItemUseCase, times(1)).perform(stockItem);
    }
}