package com.example.demo.web;

import com.example.demo.web.form.AllForm;
import com.example.demo.web.form.DetailForm;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.demo.domain.dao.Product;
import com.example.demo.domain.svc.ProductSVC;
import com.example.demo.web.form.SaveForm;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
@Controller
@RequestMapping("/products") // http://localhgost:9080/products
@RequiredArgsConstructor
public class ProductController {
    
  private final ProductSVC productSVC;
    
    // 등록양식
    @GetMapping("/add")
    public String addForm() {
      log.info("addForm호출됨");
      return "product/add";
    }

    // // 등록처리(POST), 받을때도 POST로 받아야함.
    // @PostMapping("/add1")    // POST http://localhost:9080/products/add
    // public String add1(SaveForm saveForm, RedirectAttributes redirectAttributes) {
    //   log.info("add호출됨2={}", saveForm);
    //   // 요청데이터 유효성 체크
    //   // 상품등록
    //   redirectAttributes.addAttribute("id", 1);
    //   return "redirect:/products/{id}/detail"; //GET http://localhost:9080/products/1/detail
    // }
    
    // 등록처리(GET), 받을때도 GET으로 받아야함.
    @GetMapping("/add2")    // POST http://localhost:9080/products/add
    public String add2(SaveForm saveForm, RedirectAttributes redirectAttributes) {
      log.info("add호출됨2={}", saveForm);
      // 요청데이터 유효성 체크
      // 상품등록
      Product product = new Product();
      product.setPname(saveForm.getPname());
      product.setQuantity(saveForm.getQuantity());
      product.setPrice(saveForm.getPrice());
      Long productId = productSVC.save(product);

      log.info("상품아이디={}", productId);
      redirectAttributes.addAttribute("id", productId);
      return "redirect:/products/{id}/detail";  //GET http://localhost:9080/products/1/detail
    }

    // 조회
    @GetMapping("/{id}/detail")   //GET http://localhost:6060/products/1/detail
    public String findById(@PathVariable("id") Long id , Model model) {
      // 상품조회
      Optional<Product> findedProduct = productSVC.findById(id);
      Product product = findedProduct.orElseThrow();

      DetailForm detailForm = new DetailForm();
      detailForm.setProductId(product.getProductId());
      detailForm.setPname(product.getPname());
      detailForm.setQuantity(product.getQuantity());
      detailForm.setPrice(product.getPrice());

      model.addAttribute("detailForm",detailForm);
      return "product/detailForm";
    }

    //문자열로 받기(개별로 받아진다)
//    @PostMapping("/add3")    // POST http://localhost:9080/products/add
//    @ResponseBody
//    public String add3(
//            // @RequestParam("pname") String pname, @RequestParam("quantity") String quantity, @RequestParam("price") String price;
//            @RequestBody SaveForm saveForm, RedirectAttributes redirectAttributes
//      ) {
//      log.info("add호출됨2={}", saveForm);
//      // 요청데이터 유효성 체크
//
//      // 상품등록
//      redirectAttributes.addAttribute("id", 1);
////      return "redirect:/products/{id}/detail";
//        return "ok";
//    }

  @PostMapping("/add3")    // POST http://localhost:9080/products/add
  public String add3(
          SaveForm saveForm, RedirectAttributes redirectAttributes
  ) {
    log.info("add호출됨2={}", saveForm);
    // 요청데이터 유효성 체크

    // 상품등록
    redirectAttributes.addAttribute("id", 1);
//      return "redirect:/products/{id}/detail";
    return "redirect:/products/{id}/detail";
  }

  //목록
  @GetMapping       //GET http://localhost:6060/products
  public String findAll(Model model){
      log.info("전체목록 조회 호출됨");
    List<Product> list = productSVC.findAll(); // 도메인에서 가져온 데이터. 좋은 방법이 아니다.
                                               // 도메인에서 가져온 데이터를 web쪽에 노출하지 않게 만들어야한다.
    List<AllForm> all = new ArrayList<>();
    for(Product product :list) {
      AllForm allForm = new AllForm();
      allForm.setProductId(product.getProductId());
      allForm.setPname(product.getPname());
      all.add(allForm);
    }

    model.addAttribute("all",all);
    return "product/all";
  }

  @GetMapping("/{id}/delete")    //DELETE http://localhost:6060/products/{id}/delete
  public String deleteById(@PathVariable Long id){
    int deletedRowCnt = productSVC.deleteById(id);
    return "redirect:/products";
  }

}
