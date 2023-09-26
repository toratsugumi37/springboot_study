package com.example.demo.dao;

import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Repository // DAO 역할을 하는 클래스
@RequiredArgsConstructor // final 멤버필드를 매개값으로 갖는 생성자를 자동 생성해준다.
public class ProductDAOImpl implements ProductDAO {
  
  private final NamedParameterJdbcTemplate template;

  //  생성자가 필요하지만 RequiredArgsConstructor가 lombok을 통해 자동으로 생성해준다.
  //   @Autowired 
  //   public ProductDAOImpl(NamedParameterJdbcTemplate template){
  //   this.template = template;
  //   }


  // ProductDAO의 재정의
  @Override
  public Long save(Product product) {
    StringBuffer sql = new StringBuffer();
    sql.append("insert into product(product_id,pname,quantity,price) "); //String buffer를 이용할떄는 공백을 만들어두자. (띄어쓰기가 필요한 경우가 있기때문)
    sql.append("values(product_product_id_seq.nextval,:pname, :quantity, :price) "); // :을 이용해서 외부로부터 값을 받아온다. (param을 이용함)

    SqlParameterSource param = new BeanPropertySqlParameterSource(product);
    KeyHolder keyHolder = new GeneratedKeyHolder();
    template.update(sql.toString(), param, keyHolder,new String[]{"product_id"});

    long productId = keyHolder.getKey().longValue();    //상품ID값을 읽음
    return productId;
  }
  
}
