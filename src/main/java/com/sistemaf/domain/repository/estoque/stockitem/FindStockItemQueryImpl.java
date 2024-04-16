package com.sistemaf.domain.repository.estoque.stockitem;


import com.sistemaf.domain.filter.StockItemFilter;
import com.sistemaf.domain.model.Produto_;
import com.sistemaf.domain.model.StockItem;
import com.sistemaf.infrastructure.util.interfaces.FilterUtil;
import com.sistemaf.infrastructure.util.interfaces.PageableUtil;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

public class FindStockItemQueryImpl  extends PageableUtil implements FindStockItemQuery, FilterUtil<StockItemFilter, StockItem> {

    @PersistenceContext
    private EntityManager manager;
    @Override
    public Page<StockItem> findWithFilter(StockItemFilter stockItemFilter, Pageable page) {
        CriteriaBuilder builder = manager.getCriteriaBuilder();
        CriteriaQuery<StockItem> criteriaQuery = builder.createQuery(StockItem.class);
        Root<StockItem> root = criteriaQuery.from(StockItem.class);
        Predicate[] predicates = this.criarRestricoes(stockItemFilter, builder, root);
        criteriaQuery.where(predicates);
        TypedQuery<StockItem> query = manager.createQuery(criteriaQuery);
        return new PageImpl<>(query.getResultList(), page, super.total(manager, predicates, root, StockItem.class));
    }

    @Override
    public Predicate[] criarRestricoes(StockItemFilter filter, CriteriaBuilder builder, Root<StockItem> root) {
        List<Predicate> predicateList = new ArrayList<>();
        if(!filter.getSerial().isEmpty()) {
            predicateList.add(builder.like(builder.lower(root.get("serial")),
                    "%"+filter.getSerial().toLowerCase()+"%"));
        }
        if (filter.getProductId() != null) {
            predicateList.add(builder.equal(root.get("produto").get(Produto_.id.getName()),
                    filter.getProductId()));
        }
        if (filter.getActive() != null) {
            predicateList.add(
                    builder.equal(root.get("active"), filter.getActive())
            );
        }
        return predicateList.toArray(new Predicate[predicateList.size()]);
    }
}
