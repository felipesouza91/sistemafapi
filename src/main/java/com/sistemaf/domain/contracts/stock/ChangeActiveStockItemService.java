package com.sistemaf.domain.contracts.stock;

import java.util.UUID;

public interface ChangeActiveStockItemService {
    void perform(UUID id, Boolean active);

}
