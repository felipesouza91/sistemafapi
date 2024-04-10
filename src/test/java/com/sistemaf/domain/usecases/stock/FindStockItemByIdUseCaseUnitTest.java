package com.sistemaf.domain.usecases.stock;

import com.sistemaf.domain.exception.EntityNotFoundException;
import com.sistemaf.domain.repository.StockItemRepository;
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


    @Test
    @DisplayName("should call repository with correct data ")
    public void givenID_thenPerformUseCase_thenCallRepositoryWithCorrectData() {
        UUID id = UUID.randomUUID();
        findStockItemByIdUseCase.perform(id);
        verify(stockItemRepository, times(1)).findById(id);
    }

    @Test
    @DisplayName("should throws if stock id not exits")
    public void givenInvalidID_thenPerformUseCase_thenThrows() {
        UUID id = UUID.randomUUID();
        given(stockItemRepository.findById(id)).willReturn(Optional.empty());
        Exception exception = assertThrows(EntityNotFoundException.class, () -> findStockItemByIdUseCase.perform(id));
        assertThat(exception.getMessage(), is("O Item não foi encontrado"));
    }
}