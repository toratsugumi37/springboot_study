package com.example.demo.domain.svc;

import com.example.demo.domain.dao.Product;

import java.util.List;
import java.util.Optional;

public interface ProductSVC {
  Long save(Product product);

  Optional<Product> findById(Long productid);

  List<Product> findAll();

  int deleteById(Long productId);
}


