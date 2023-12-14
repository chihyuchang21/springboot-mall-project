package com.example.spboot.service;

import com.example.spboot.dto.ProductRequest;
import com.example.spboot.model.Product;

public interface ProductService {

    Product getProductById(Integer productId);

    Integer createProduct(ProductRequest productRequest);

    void updateProduct(Integer productId, ProductRequest productRequest);

}
