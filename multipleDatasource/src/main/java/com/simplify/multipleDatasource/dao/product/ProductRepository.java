package com.simplify.multipleDatasource.dao.product;

import com.simplify.multipleDatasource.model.product.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Integer> {
}
