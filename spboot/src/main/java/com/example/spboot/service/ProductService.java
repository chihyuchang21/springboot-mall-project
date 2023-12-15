package com.example.spboot.service;

import com.example.spboot.constant.ProductCategory;
import com.example.spboot.dto.ProductRequest;
import com.example.spboot.model.Product;

import java.util.List;

public interface ProductService {

    List<Product> getProducts(ProductCategory category,String search);

    Product getProductById(Integer productId);

    Integer createProduct(ProductRequest productRequest);

    void updateProduct(Integer productId, ProductRequest productRequest);

    void deleteProductById(Integer productId);

}
