package com.example.demo2.dao.Interface;

import com.example.demo2.constant.ProductCategory;
import com.example.demo2.dto.ProductRequest;
import com.example.demo2.dto.ProductRequestParams;
import com.example.demo2.modal.Product;

import java.util.List;

public interface IProduct {
    //Product getProductById(Integer id);
    Integer createProduct(ProductRequest productRequest);
    Integer updateProduct(Integer id,ProductRequest productRequest);
    Integer deleteProduct(Integer id);
    List<Product> getProduct(ProductRequestParams productRequestParams);
    Integer countProduct(ProductRequestParams productRequestParams);
}
