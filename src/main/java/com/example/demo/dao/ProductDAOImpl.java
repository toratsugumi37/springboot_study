package com.example.demo.dao;

import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Repository
@RequiredArgsConstructor
public class ProductDAOImpl implements ProductDAO {
  
  private final NamedParameterJdbcTemplate template;

  @Override
  public Long save(Product product) {
    // template.
    return null;
  }
}
