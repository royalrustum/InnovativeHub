package com.innovatehub.inventorymgmt.common.repository.stock;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.innovatehub.inventorymgmt.common.entity.stock.Stock;

@Repository
public interface StockRepository extends JpaRepository<Stock, Long> {

}
