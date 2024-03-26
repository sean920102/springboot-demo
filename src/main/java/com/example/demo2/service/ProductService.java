package com.example.demo2.service;

import com.example.demo2.dao.ProductDao;
import com.example.demo2.dto.ProductRequest;
import com.example.demo2.dto.ProductRequestParams;
import com.example.demo2.modal.Product;
import com.example.demo2.service.IService.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ProductService implements IProductService {
    @Autowired
    private ProductDao productDao;

    @Override
    public Product getProductById(Integer id) {
        return productDao.getProductById(id);
    }

    @Override
    public List<Product> getProduct(ProductRequestParams productRequestParams) {
        return productDao.getProduct(productRequestParams);
    }

    @Override
    public Integer createProduct(ProductRequest productRequest) {
        return productDao.createProduct(productRequest);
    }

    @Override
    public Integer updateProduct(Integer id ,ProductRequest productRequest) {
        return productDao.updateProduct(id,productRequest);
    }

    @Override
    public Integer deleteProduct(Integer id) {
        return productDao.deleteProduct(id);
    }

    @Override
    public Integer countProduct(ProductRequestParams productRequestParams) {
        return productDao.countProduct(productRequestParams);
    }
}
