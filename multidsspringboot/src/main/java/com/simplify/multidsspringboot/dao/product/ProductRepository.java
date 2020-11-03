package com.simplify.multidsspringboot.dao.product;

import com.simplify.multidsspringboot.model.product.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Integer> {
}
