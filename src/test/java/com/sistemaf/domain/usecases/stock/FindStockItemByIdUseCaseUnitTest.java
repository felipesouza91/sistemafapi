package com.sistemaf.domain.usecases.stock;

import com.sistemaf.domain.exception.EntityNotFoundException;
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
import java.util.UUID;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class FindStockItemByIdUseCaseUnitTest {

    @Spy
    private StockItemRepository stockItemRepository;

    @InjectMocks
    private FindStockItemByIdUseCase findStockItemByIdUseCase;

    private StockItem data = Instancio.create(StockItem.class);

    @Test
    @DisplayName("should call repository with correct data ")
    public void givenID_thenPerformUseCase_thenCallRepositoryWithCorrectData() {

        given(stockItemRepository.findById(data.getId())).willReturn(Optional.of(data));
        findStockItemByIdUseCase.perform(data.getId());
        verify(stockItemRepository, times(1)).findById(data.getId());
    }

    @Test
    @DisplayName("should throws if stock id not exits")
    public void givenInvalidID_thenPerformUseCase_thenThrows() {
        UUID id = UUID.randomUUID();
        given(stockItemRepository.findById(id)).willReturn(Optional.empty());
        Exception exception = assertThrows(EntityNotFoundException.class, () -> findStockItemByIdUseCase.perform(id));
        assertThat(exception.getMessage(), is("O Item não foi encontrado"));
    }

    @Test
    @DisplayName("should return Stock Item if id exits")
    public void givenValidID_thenPerformUseCase_thenReturnStockItem() {
        given(stockItemRepository.findById(data.getId())).willReturn(Optional.of(data));
        StockItem stockItem = findStockItemByIdUseCase.perform(data.getId());
        assertThat(data.getSerial(), is(stockItem.getSerial()));
        assertThat(data.getId(), is(stockItem.getId()));

    }
}