package com.example.spboot.controller;

import com.example.spboot.constant.ProductCategory;
import com.example.spboot.dto.ProductQueryParams;
import com.example.spboot.dto.ProductRequest;
import com.example.spboot.model.Product;
import com.example.spboot.service.ProductService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//表示此為Controller層的Bean
@RestController
public class ProductController {

    @Autowired
    private ProductService productService;

    //查詢商品列表
    @GetMapping("/products")
    public ResponseEntity<List<Product>> getProducts(
            @RequestParam(required = false) ProductCategory category, //想查看哪一類商品，把category的值一路從Controller層傳到Dao裡面
            @RequestParam(required = false) String search //search也用相同方法傳入Dao
    ){
        ProductQueryParams productQueryParams = new ProductQueryParams();
        ProductQueryParams.setCategory(category); //把前端傳過來的值set到這個變數中
        ProductQueryParams.setSearch(search);

        List<Product> productList = productService.getProducts(productQueryParams);

        //返回productList給前端
        return ResponseEntity.status(HttpStatus.OK).body(productList);
    }



    //Read Data(one specific product)
    @GetMapping("/products/{productId}")
    public ResponseEntity<Product> getProduct(@PathVariable Integer productId){
        //需使用ProductServie中的方法
        Product product = productService.getProductById(productId);

        if(product != null){
            return ResponseEntity.status(HttpStatus.OK).body(product);
        }else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    //Create Data //@Valid容易忘記加
    @PostMapping("/products")
    public ResponseEntity<Product> createProduct(@RequestBody @Valid ProductRequest productRequest){
        //回傳Int，使用pS中的cP方法(含有pR參數)
        Integer productId = productService.createProduct(productRequest);

        Product product = productService.getProductById(productId);

        return ResponseEntity.status(HttpStatus.CREATED).body(product);
    }

    @PutMapping("/products/{productId}")
    public ResponseEntity<Product> updateProduct(@PathVariable Integer productId,
                                                 @RequestBody @Valid ProductRequest productRequest){ //先接住url傳過來的值(PathVariable)
        //先檢查product是否存在
        Product product = productService.getProductById(productId);

        if (product == null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

        //修改商品的數量
        productService.updateProduct(productId,productRequest);
        //接下來去Service層中把updateProduct實作出來

        Product updatedProduct = productService.getProductById(productId);

        return ResponseEntity.status(HttpStatus.OK).body(updatedProduct);
    }

    @DeleteMapping("/products/{productId}")
    public ResponseEntity<?> deleteProduct(@PathVariable Integer productId){
        productService.deleteProductById(productId);

        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();

        //不用檢查商品是否存在、商品存在，成功的刪除，前端不在意是否刪除只在意結果

    }



}
