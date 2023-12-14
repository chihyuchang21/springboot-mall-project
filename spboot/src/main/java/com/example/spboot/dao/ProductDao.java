package com.example.spboot.dao;

import com.example.spboot.dto.ProductRequest;
import com.example.spboot.model.Product;

public interface ProductDao {

    Product getProductById(Integer productId);

    Integer createProduct(ProductRequest productRequest);

    void updateProduct(Integer productId, ProductRequest productRequest);

}
