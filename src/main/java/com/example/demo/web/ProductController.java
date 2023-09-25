package com.example.demo.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.demo.web.form.SaveForm;

import lombok.extern.slf4j.Slf4j;

@Slf4j
  @Controller
  @RequestMapping("/products")    // http://localhgost:9080/products
  public class ProductController {
    
    // 등록양식
    @GetMapping("/add")
    public String addForm() {
      log.info("addForm호출됨");
      return "product/add";
    }

    // 등록처리(POST), 받을때도 POST로 받아야함.
    @PostMapping("/add1")    // POST http://localhost:9080/products/add
    public String add1(SaveForm saveForm, RedirectAttributes redirectAttributes) {
      log.info("add호출됨2={}", saveForm);
      // 요청데이터 유효성 체크
      // 상품등록
      redirectAttributes.addAttribute("id", 1);
      return "redirect:/products/{id}/detail"; //GET http://localhost:9080/products/1/detail
    }
    
    // 등록처리(GET), 받을때도 GET으로 받아야함.
    // @GetMapping("/add2")    // POST http://localhost:9080/products/add
    // public String add2(SaveForm saveForm, RedirectAttributes redirectAttributes) {
    //   log.info("add호출됨2={}", saveForm);
    //   // 요청데이터 유효성 체크
    //   // 상품등록
    //   redirectAttributes.addAttribute("id", 1);
    //   return "redirect:/products/{id}/detail";  //GET http://localhost:9080/products/1/detail
    // }

    // // 조회
    // @GetMapping("/{id}/detail")
    // public String findById(@PathVariable("id") Long id) {
    //   // 상품조회

    //   return "product/detailForm";
    // }

    // //문자열로 받기(개별로 받아진다)
    // @GetMapping("/add3")    // POST http://localhost:9080/products/add
    // public String add3(
    //   @RequestParam("pname") String pname, @RequestParam("quantity") String quantity, @RequestParam("price") String price;
    //     // SaveForm saveForm, RedirectAttributes redirectAttributes
    //   ) {
    //   log.info("add호출됨2={}", saveForm);
    //   // 요청데이터 유효성 체크

    //   // 상품등록
    //   redirectAttributes.addAttribute("id", 1);
    //   return "redirect:/products/{id}/detail";  //GET http://localhost:9080/products/1/detail
    // }
    

}
