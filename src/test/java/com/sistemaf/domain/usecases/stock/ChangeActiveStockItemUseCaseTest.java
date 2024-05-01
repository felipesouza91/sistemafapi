package com.sistemaf.domain.usecases.stock;

import com.sistemaf.domain.exception.EntityNotFoundException;
import com.sistemaf.domain.repository.estoque.stockitem.StockItemRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.UUID;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.isA;
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

    @Test
    @DisplayName("should throws if id not find")
    public void givenInvalidId_whenPerform_thenThrows() {
        UUID id = UUID.randomUUID();
        Exception exception = assertThrows(EntityNotFoundException.class, () -> sut.perform(id, true));
        assertThat(exception.getMessage(), is("O item do stock nao foi encontrado"));
    }
}