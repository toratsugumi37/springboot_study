package com.example.demo.domain.dao;

import lombok.Data;

@Data
public class Product {
  private long productId;   //PRODUCT_ID	NUMBER(10,0)
  private String pname;     //PNAME	VARCHAR2(30 BYTE)
  private long quantity;     //QUANTITY	NUMBER(10,0)
  private long price;       //PRICE	NUMBER(10,0)
}

// Entity 클래스이다.