package com.example.demo.domain.dao;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Slf4j
@Repository // DAO 역할을 하는 클래스
@RequiredArgsConstructor // final 멤버필드를 매개값으로 갖는 생성자를 자동 생성해준다.
public class ProductDAOImpl implements ProductDAO {
  private final NamedParameterJdbcTemplate template;
  // ProductDAO의 재정의
  @Override
  public Long save(Product product) {
    StringBuffer sql = new StringBuffer();
    sql.append("insert into product(product_id,pname,quantity,price) "); //String buffer를 이용할떄는 공백을 만들어두자. (띄어쓰기가 필요한 경우가 있기때문)
    sql.append("values(product_product_id_seq.nextval,:pname, :quantity, :price) "); // :을 이용해서 외부로부터 값을 받아온다. (param을 이용함)

    SqlParameterSource param = new BeanPropertySqlParameterSource(product);
    KeyHolder keyHolder = new GeneratedKeyHolder();
    template.update(sql.toString(), param, keyHolder, new String[] { "product_id" });

    long productId = keyHolder.getKey().longValue(); //상품ID값을 읽음
    return productId;
  }

  //  람다
  // private RowMapper<Product> productRowMapper() {
  //   return (rs, rowNum) -> {
  //     Product product = new Product();
  //     product.setProductId(rs.getLong("product_id"));
  //     product.setPname(rs.getString("pname"));
  //     product.setQuantity(rs.getLong("quantity"));
  //     product.setPrice(rs.getLong("price"));

  //     return product;
  //   };
  // }
  
  private RowMapper<Product> productRowMapper() {
    return (rs, rowNum) -> {
      Product product = new Product();
      product.setProductId(rs.getLong("product_id"));
      product.setPname(rs.getString("pname"));
      product.setQuantity(rs.getLong("quantity"));
      product.setPrice(rs.getLong("price"));

      return product;
    };
  }
  
  MyRowMapper myRowMapper = new MyRowMapper();
  
  @Override
  public Optional<Product> findById(Long productId) {
    StringBuffer sql = new StringBuffer();
    sql.append("select product_id, pname, quantity, price from product where product_id = :id");

    try{
    //조회 : (단일행, 단일열), (단일행, 다중열), (다중행, 단일열), (다중행, 다중열) 
    // 값은  ProductDAO에 저장한다.
    Map<String,Long> param = Map.of("id",productId);
    Product product = template.queryForObject(sql.toString(), param, myRowMapper);
    return Optional.of(product);
  } catch (EmptyResultDataAccessException e) {
      
    }
    return Optional.empty();
  }

  @Override
  public List<Product> findAll() {
    StringBuffer sql = new StringBuffer();
    sql.append("select product_id, pname, quantity, price ");
    sql.append("from product ");
    sql.append("order by product_id asc");

      List<Product> list = template.query(sql.toString(), BeanPropertyRowMapper.newInstance(Product.class));
      return list;
  }

  @Override
  public int deleteById(Long productId) {
    String sql = "delete from product where product_id = :productId";
    int deletedRowCnt = template.update(sql, Map.of("productId", productId));

    return deletedRowCnt;
  }
}
