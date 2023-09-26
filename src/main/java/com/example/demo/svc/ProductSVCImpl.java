package com.example.demo.svc;

import org.springframework.stereotype.Service;

import com.example.demo.dao.Product;
import com.example.demo.dao.ProductDAO;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class ProductSVCImpl implements ProductSVC {
  
  private final ProductDAO productDAO;

  

  @Override
  public Long save(Product product) {
    Long productId = productDAO.save(product);
    return productId;
  }  
}
