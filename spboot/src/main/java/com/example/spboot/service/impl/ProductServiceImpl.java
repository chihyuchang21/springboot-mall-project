package com.example.spboot.service.impl;

import com.example.spboot.dao.ProductDao;
import com.example.spboot.model.Product;
import com.example.spboot.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductDao productDao;

    @Override
    //service層實作簡單，直接去call getProductById即可
    public Product getProductById(Integer productId) {
        return productDao.getProductById(productId);
    }
}
