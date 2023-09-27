package com.example.demo.domain.svc;

import org.springframework.stereotype.Service;

import com.example.demo.domain.dao.Product;
import com.example.demo.domain.dao.ProductDAO;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class ProductSVCImpl implements ProductSVC {
  
  private final ProductDAO productDAO;

  

  @Override
  public Long save(Product product) {
    return productDAO.save(product);
  }

  @Override
  public Optional<Product> findById(Long productId){
    return productDAO.findById(productId);
  }

  @Override
  public List<Product> findAll() {
    return productDAO.findAll();
  }

  @Override
  public int deleteById(Long productId) {
    return productDAO.deleteById(productId);
  }
}
