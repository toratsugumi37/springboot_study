package com.example.demo.dao;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootTest
public class ProductDAOImplTest {
  
  @Autowired
  ProductDAO productDAO; //스프링 컨테이너의 빈에서 ProductDAO를 productDAO에 주입받기

  //등록
  @Test
  @DisplayName("상품등록")
  void save(){
    Product product = new Product();
    product.setPname("상품1");
    product.setQuantity(10L);
    product.setPrice(10000L);
    Long productId = productDAO.save(product);
    log.info("상품아이디 ={}", productId);
    Assertions.assertThat(productId).isGreaterThan(0L);
  }
}
