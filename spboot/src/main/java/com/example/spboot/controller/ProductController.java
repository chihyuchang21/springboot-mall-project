package com.example.spboot.controller;

import com.example.spboot.constant.ProductCategory;
import com.example.spboot.dto.ProductQueryParams;
import com.example.spboot.dto.ProductRequest;
import com.example.spboot.model.Product;
import com.example.spboot.service.ProductService;
import com.example.spboot.util.Page;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Validated //易忘
//表示此為Controller層的Bean
@RestController
public class ProductController {

    @Autowired
    private ProductService productService;

    //查詢商品列表
    @GetMapping("/products")
    public ResponseEntity<Page<Product>> getProducts(
            //查詢條件 Filtering
            @RequestParam(required = false) ProductCategory category, //想查看哪一類商品，把category的值一路從Controller層傳到Dao裡面
            @RequestParam(required = false) String search, //search也用相同方法傳入Dao

            //排序 Sorting
            @RequestParam(defaultValue = "created_date") String orderBy, //根據什麼欄位排序
            @RequestParam(defaultValue = "desc") String sort, //升序or降序(desc)

            //分頁 Pagination //都是從url中取得的請求參數
            @RequestParam(defaultValue = "5") @Max(1000) @Min(0) Integer limit, //取得幾筆商品數據
            @RequestParam(defaultValue = "0") @Min(0) Integer offset //跳過幾筆數據
    ) {
        //把前端傳過來的值set到這個變數中
        ProductQueryParams productQueryParams = new ProductQueryParams();
        productQueryParams.setCategory(category);
        productQueryParams.setSearch(search);
        productQueryParams.setOrderBy(orderBy);
        productQueryParams.setSort(sort);
        productQueryParams.setLimit(limit);
        productQueryParams.setOffset(offset);

        //取得 product list
        List<Product> productList = productService.getProducts(productQueryParams); //improved

        //取得 product 總數
        Integer total = productService.countProduct(productQueryParams);

        //分頁
        Page<Product> page = new Page<>();
        page.setLimit(limit);
        page.setOffset(offset);
        page.setTotal(total); //尚未實作
        page.setResults(productList);

        //返回productList給前端
        return ResponseEntity.status(HttpStatus.OK).body(page);
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
