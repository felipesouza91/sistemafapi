package com.sistemaf.domain.usecases.stock;

import com.sistemaf.domain.repository.estoque.stockitem.StockItemRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class ChangeActiveStockItemUseCaseTest {

    @Spy
    private StockItemRepository stockItemRepository;
    @InjectMocks
    private ChangeActiveStockItemUseCase sut;

    @Test
    @DisplayName("should call repository with correct value")
    public void given_whenPerform_thenCallRepositoryCorrect() {
        UUID id = UUID.randomUUID();
        sut.perform(id, true);
        verify(stockItemRepository, times(1)).findById(id);
    }
}