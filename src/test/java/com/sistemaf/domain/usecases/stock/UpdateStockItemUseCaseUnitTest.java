package com.sistemaf.domain.usecases.stock;

import com.sistemaf.domain.exception.BusinessException;
import com.sistemaf.domain.exception.EntityNotFoundException;
import com.sistemaf.domain.model.Produto;
import com.sistemaf.domain.model.StockItem;
import com.sistemaf.domain.repository.StockItemRepository;
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

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.instancio.Select.field;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class UpdateStockItemUseCaseUnitTest {

    @Mock
    private StockItemRepository stockItemRepository;

    @Mock
    private ProdutoRepository productRepository;

    @Spy
    @InjectMocks
    private UpdateStockItemUseCase updateStockItemUseCase;

    private final StockItem mockInputValue = Instancio.of(StockItem.class).ignore(field(StockItem::getId)).create();

    @Test
    @DisplayName("should call service with correct data")
    public void given_whenUpdateStockItem_thenCallWithCorrectItem() {

        UUID id = UUID.randomUUID();
        given(stockItemRepository.findById(id)).willReturn(Optional.of(mockInputValue));
        given(productRepository.findById(mockInputValue.getProduto().getId())).willReturn(Optional.of(mockInputValue.getProduto()));

        updateStockItemUseCase.perform(id, mockInputValue);
        verify(updateStockItemUseCase, times(1)).perform(id, mockInputValue);
    }

    @Test
    @DisplayName("should throws if id not exits")
    public void givenInvalidId_whenUpdateStockItem_thenThrows() {
        UUID id = UUID.randomUUID();
        given(stockItemRepository.findById(id)).willReturn(Optional.empty());
        Exception exception = assertThrows(EntityNotFoundException.class, () -> updateStockItemUseCase.perform(id, mockInputValue));
        assertThat(exception.getMessage(), is("O item não foi encontrado"));
    }

    @Test
    @DisplayName("should throws if product not exits")
    public void givenInvalidProductId_whenUpdateStockItem_thenThrows() {
        UUID id = UUID.randomUUID();
        mockInputValue.setId(id);
        given(stockItemRepository.findById(id)).willReturn(Optional.of(mockInputValue));
        given(productRepository.findById(mockInputValue.getProduto().getId())).willReturn(Optional.empty());
        Exception exception = assertThrows(EntityNotFoundException.class, () -> updateStockItemUseCase.perform(id, mockInputValue));
        assertThat(exception.getMessage(), is("O produto não foi encontrado"));
    }

    @Test
    @DisplayName("should throws if serial exits with different id ")
    public void givenValidData_whenUpdateStockItem_ThenSuccess() {
        UUID id = UUID.randomUUID();
        Produto product = mockInputValue.getProduto();
        StockItem stockItem = Instancio.create(StockItem.class);
        given(stockItemRepository.findById(id)).willReturn(Optional.of(mockInputValue));
        given(stockItemRepository.findBySerial(mockInputValue.getSerial())).willReturn(Optional.of(stockItem));
        given(productRepository.findById(product.getId())).willReturn(Optional.of(product));
        Exception exception = assertThrows(BusinessException.class, () -> updateStockItemUseCase.perform(id, mockInputValue));
        assertThat(exception.getMessage(), is("O serial já esta cadastrado em outro item"));
    }
}
