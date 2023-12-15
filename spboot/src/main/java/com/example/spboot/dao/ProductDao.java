package com.example.spboot.dao;

import com.example.spboot.dto.ProductQueryParams;
import com.example.spboot.dto.ProductRequest;
import com.example.spboot.model.Product;

import java.util.List;

public interface ProductDao {

    List<Product> getProducts(ProductQueryParams productQueryParams);

    Product getProductById(Integer productId);

    Integer createProduct(ProductRequest productRequest);

    void updateProduct(Integer productId, ProductRequest productRequest);

    void deleteProductById(Integer productId);

}
