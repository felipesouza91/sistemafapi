package com.sistemaf.domain.usecases.stock;

import com.sistemaf.domain.exception.EntityNotFoundException;
import com.sistemaf.domain.model.StockItem;
import com.sistemaf.domain.repository.estoque.stockitem.StockItemRepository;
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
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.BDDMockito.given;
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
        StockItem stockItem = Instancio.create(StockItem.class);

        given(stockItemRepository.findById(stockItem.getId())).willReturn(Optional.of(stockItem));
        sut.perform(stockItem.getId(), true);
        verify(stockItemRepository, times(1)).findById(stockItem.getId());
    }

    @Test
    @DisplayName("should throws if id not find")
    public void givenInvalidId_whenPerform_thenThrows() {
        UUID id = UUID.randomUUID();
        Exception exception = assertThrows(EntityNotFoundException.class, () -> sut.perform(id, true));
        assertThat(exception.getMessage(), is("O item do stock nao foi encontrado"));
    }

    @Test
    @DisplayName("should call repository save method with correct values")
    public void givenData_whenPerform_thenCallDataCorrect() {
        StockItem stockItem = Instancio.create(StockItem.class);
        Boolean inverseValue = !stockItem.getActive();
        given(stockItemRepository.findById(stockItem.getId())).willReturn(Optional.of(stockItem));
        sut.perform(stockItem.getId(), inverseValue);
        verify(stockItemRepository, times(1)).save(argThat( argument -> {
            assertThat(argument.getActive(), is(inverseValue));
            return true;
        }));
    }

    @Test
    @DisplayName("should throws if repository throws")
    public void given_whenPerform_thenThrows() {
        given(stockItemRepository.findById(any())).willThrow(new RuntimeException());
        assertThrows(Exception.class, () -> sut.perform(UUID.randomUUID(), true));
    }
}