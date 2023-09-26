package com.example.demo.dao;

import java.util.Optional;

public interface ProductDAO {
  // 등록
  Long save(Product product);

  // 저장
  // Product findById(Long id); //무조건 상품이 조회되어야함
  Optional<Product> findById(Long productid); //상품이 반환될수도 있고 아닐수도있음
}
