package com.sistemaf.domain.usecases.stock;

import com.sistemaf.domain.contracts.stock.UpdateStockItemService;
import com.sistemaf.domain.exception.BusinessException;
import com.sistemaf.domain.exception.EntityNotFoundException;
import com.sistemaf.domain.model.Produto;
import com.sistemaf.domain.model.StockItem;
import com.sistemaf.domain.repository.StockItemRepository;
import com.sistemaf.domain.repository.estoque.produto.ProdutoRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
@AllArgsConstructor
public class UpdateStockItemUseCase implements UpdateStockItemService {

    private StockItemRepository stockItemRepository;
    private ProdutoRepository productRepositoy;

    @Override
    public StockItem perform(UUID id, StockItem data) {
        Optional<StockItem> exitsStockItem = this.stockItemRepository.findById(id);
        if(exitsStockItem.isEmpty()) {
            throw new EntityNotFoundException("O item não foi encontrado");
        }
        Optional<Produto> exitsDataProduct = this.productRepositoy.findById(data.getProduto().getId());
        if(exitsDataProduct.isEmpty()) {
            throw new EntityNotFoundException("O produto não foi encontrado");
        }
        Optional<StockItem> exitsSerial = this.stockItemRepository.findBySerial(data.getSerial());
        exitsSerial.ifPresent((value) -> {
            if(!value.getId().equals(id)) {
                throw new BusinessException("O serial já esta cadastrado em outro item");
            }
        });
        return null;
    }
}
