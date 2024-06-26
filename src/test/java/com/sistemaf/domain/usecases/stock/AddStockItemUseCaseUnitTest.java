package com.sistemaf.domain.usecases.stock;


import com.sistemaf.domain.exception.BusinessException;
import com.sistemaf.domain.model.StockItem;
import com.sistemaf.domain.repository.estoque.stockitem.StockItemRepository;
import com.sistemaf.domain.repository.estoque.produto.ProdutoRepository;
import org.instancio.Instancio;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class AddStockItemUseCaseUnitTest {

    @Mock
    private ProdutoRepository productRepository;

    @Spy
    private StockItemRepository stockItemRepository;

    @Spy
    @InjectMocks
    private AddStockItemUseCase addStockItemUseCase;
    StockItem inputData = Instancio.create(StockItem.class);

    @Test
    @DisplayName("should called with correct values")
    public void givenInputData_whenExecuteUseCase_thenCalledWithCorrectItem() {
        inputData.setId(null);
        given(productRepository.findById(inputData.getProduto().getId())).willReturn(Optional.of(inputData.getProduto()));
        addStockItemUseCase.perform(inputData);
        verify(addStockItemUseCase, times(1)).perform(inputData);
    }

    @Test
    @DisplayName("should call findBySerial repository with correct value")
    public void givenInputData_whenExecuteUseCase_thenCalledFindBySerialRepository() {
        inputData.setId(null);
        given(productRepository.findById(inputData.getProduto().getId())).willReturn(Optional.of(inputData.getProduto()));

        addStockItemUseCase.perform(inputData);
        verify(stockItemRepository, times(1)).findBySerial(inputData.getSerial());
    }

    @Test
    @DisplayName("should call throw if serial exists")
    public void givenInputData_whenExecuteUseCase_thenThrowsIfSerialExists() {
        inputData.setId(null);

        given(stockItemRepository.findBySerial(inputData.getSerial())).willReturn(Optional.of(inputData));
        Exception exception = assertThrows(BusinessException.class, () ->  addStockItemUseCase.perform(inputData));
        assertThat(exception.getMessage(), is("O serial já esta cadastrado"));
    }

    @Test
    @DisplayName("should call save stock item repository method with correct values")
    public void givenInputData_whenExecuteUseCase_thenCallSaveRepositoryWithCorrectData() {
        inputData.setId(null);
        given(productRepository.findById(inputData.getProduto().getId())).willReturn(Optional.of(inputData.getProduto()));
        addStockItemUseCase.perform(inputData);
        verify(stockItemRepository, times(1)).save(inputData);
    }

    @Test
    @DisplayName("should return saved stock item")
    public void givenInputData_whenExecuteUseCase_thenReturnSavedStockItem() {
        inputData.setId(null);
        given(productRepository.findById(inputData.getProduto().getId())).willReturn(Optional.of(inputData.getProduto()));
        given(stockItemRepository.findBySerial(inputData.getSerial())).willReturn(Optional.empty());
        given(stockItemRepository.save(inputData)).will( args -> {
            StockItem stockItem1 = args.getArgument(0);
            stockItem1.setId(UUID.randomUUID());
            return stockItem1;
        });
        StockItem savedStockItem =  addStockItemUseCase.perform(inputData);
        assertNotNull(savedStockItem.getId());
        assertThat(savedStockItem.getSerial(), is(inputData.getSerial()));
        assertNotNull(savedStockItem.getCreatedAt());
    }

    @Test
    @DisplayName("should throws if product do not exits")
    public void givenInvalidProduct_whenExecuteUseCase_thenThrows() {
        inputData.setId(null);
        given(productRepository.findById(inputData.getProduto().getId())).willReturn(Optional.empty());
        Exception exception = assertThrows(BusinessException.class, () -> addStockItemUseCase.perform(inputData));
        assertThat(exception.getMessage(), is("O produto informado não foi encontrado"));
    }
}