package com.example.demo.dao;

import java.util.List;
import java.util.Optional;

import com.example.demo.domain.dao.Product;
import com.example.demo.domain.dao.ProductDAO;
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
  void save() {
    Product product = new Product();
    product.setPname("상품1");
    product.setQuantity(10L);
    product.setPrice(10000L);
    Long productId = productDAO.save(product);
    log.info("상품아이디 ={}", productId);
    Assertions.assertThat(productId).isGreaterThan(0L);
  }
  
  @Test
  @DisplayName("조회")
  void findById() {

    Optional<Product> product = productDAO.findById(82L);
    // if(product.isPresent()){
    //   log.info("ㅇㅇ");
    // }else{
    //   log.info("조회결과 없음");
    // }
    Product findedProduct = product.orElseThrow();
    Assertions.assertThat(findedProduct.getPname()).isEqualTo("상품1");
    Assertions.assertThat(findedProduct.getQuantity()).isEqualTo(10L);
    Assertions.assertThat(findedProduct.getPrice()).isEqualTo(10000L);

  }

  @Test
  @DisplayName("목록")
  void findAll(){
    List<Product> list = productDAO.findAll();
    log.info("목록={}", list);
    Assertions.assertThat(list.size()).isGreaterThan(0);
  }

  @Test
  @DisplayName("단건삭제")
  void deleteById(){
    Long productId = 92L;
    int deletedRowCnt = productDAO.deleteById(productId);

    Assertions.assertThat(deletedRowCnt).isEqualTo(1);
  }
}
