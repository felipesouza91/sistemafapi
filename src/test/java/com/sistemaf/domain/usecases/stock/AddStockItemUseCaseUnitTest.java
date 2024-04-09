package com.sistemaf.domain.usecases.stock;


import com.sistemaf.domain.exception.BusinessException;
import com.sistemaf.domain.model.StockItem;
import com.sistemaf.domain.repository.StockItemRepository;
import org.instancio.Instancio;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.mockito.BDDMockito.given;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class AddStockItemUseCaseUnitTest {

    @Spy
    private StockItemRepository stockItemRepository;

    @Spy
    @InjectMocks
    private AddStockItemUseCase addStockItemUseCase;

    @Test
    @DisplayName("should called with correct values")
    public void givenInputData_whenExecuteUseCase_thenCalledWithCorrectItem() {
        StockItem stockItem = Instancio.create(StockItem.class);
        stockItem.setId(null);
        addStockItemUseCase.perform(stockItem);
        verify(addStockItemUseCase, times(1)).perform(stockItem);
    }

    @Test
    @DisplayName("should call findBySerial repository with correct value")
    public void givenInputData_whenExecuteUseCase_thenCalledFindBySerialRepository() {
        StockItem stockItem = Instancio.create(StockItem.class);
        stockItem.setId(null);
        addStockItemUseCase.perform(stockItem);
        verify(stockItemRepository, times(1)).findBySerial(stockItem.getSerial());
    }

    @Test
    @DisplayName("should call throw if serial exists")
    public void givenInputData_whenExecuteUseCase_thenThrowsIfSerialExists() {
        StockItem stockItem = Instancio.create(StockItem.class);
        stockItem.setId(null);
        given(stockItemRepository.findBySerial(stockItem.getSerial())).willReturn(Optional.of(stockItem));
        Exception exception = assertThrows(BusinessException.class, () ->  addStockItemUseCase.perform(stockItem));
        assertThat(exception.getMessage(), is("O serial já esta cadastrado"));
    }
}