package com.sistemaf.domain.usecases.stock;

import com.sistemaf.domain.contracts.stock.AddStockItemService;
import com.sistemaf.domain.exception.BusinessException;
import com.sistemaf.domain.model.Produto;
import com.sistemaf.domain.model.StockItem;
import com.sistemaf.domain.repository.estoque.stockitem.StockItemRepository;
import com.sistemaf.domain.repository.estoque.produto.ProdutoRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class AddStockItemUseCase implements AddStockItemService {

    private final ProdutoRepository productRepository;

    private final StockItemRepository stockItemRepository;
    @Override
    public StockItem perform(StockItem data) {
        Optional<StockItem> usedStockItem = this.stockItemRepository.findBySerial(data.getSerial());
        if(usedStockItem.isPresent()) {
            throw new BusinessException("O serial já esta cadastrado");
        }
        Optional<Produto> productExists = this.productRepository.findById(data.getProduto().getId());
        if( productExists.isEmpty() ) {
            throw new BusinessException("O produto informado não foi encontrado");
        }
        return this.stockItemRepository.save(data);
    }
}
