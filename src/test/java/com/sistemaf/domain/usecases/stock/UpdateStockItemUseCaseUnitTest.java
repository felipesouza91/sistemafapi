package com.sistemaf.domain.usecases.stock;

import com.sistemaf.domain.model.StockItem;
import org.instancio.Instancio;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.UUID;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class UpdateStockItemUseCaseUnitTest {


    @Spy
    private UpdateStockItemUseCase updateStockItemUseCase;


    @Test
    @DisplayName("should call service with correct data")
    public void given_whenUpdateStockItem_thenCallWithCorrectItem() {
        StockItem stockItem = Instancio.create(StockItem.class);
        stockItem.setId(null);
        UUID id = UUID.randomUUID();
        updateStockItemUseCase.perform(id, stockItem);
        verify(updateStockItemUseCase, times(1)).perform(id, stockItem);
    }
}
