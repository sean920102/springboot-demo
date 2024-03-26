package com.example.demo2.controller;

import com.example.demo2.constant.ProductCategory;
import com.example.demo2.dto.ProductRequest;
import com.example.demo2.dto.ProductRequestParams;
import com.example.demo2.modal.Product;
import com.example.demo2.service.ProductService;
import com.example.demo2.util.Page;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.config.annotation.CorsRegistry;

import java.util.List;

@RequestMapping("/products")
@RestController
@Validated
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping()
    public ResponseEntity<Page<Product>> queryProduct(
            @RequestParam(required = false) ProductCategory productCategory,
            @RequestParam(required = false) String search,
            @RequestParam(defaultValue = "created_date") String orderBy,
            @RequestParam(defaultValue = "DESC") String sort,
            @RequestParam(defaultValue = "5") @Max(1000) @Min(0) Integer limit,
            @RequestParam(defaultValue = "0") @Min(0) Integer offset
    ){
        ProductRequestParams productRequestParams = new ProductRequestParams();
        productRequestParams.setProductCategory(productCategory);
        productRequestParams.setSearch(search);
        productRequestParams.setOrderby(orderBy);
        productRequestParams.setSort(sort);
        productRequestParams.setLimit(limit);
        productRequestParams.setOffset(offset);

        List<Product> productList = productService.getProduct(productRequestParams);
        Integer total = productService.countProduct(productRequestParams);
        Page<Product> product = new Page<>();
        product.setLimit(limit);
        product.setOffset(offset);
        product.setTotal(total);
        product.setResult(productList);
        return ResponseEntity.status(HttpStatus.OK).body(product);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Product> queryProductById(@PathVariable Integer id){
        Product product =  productService.getProductById(id);
        if (product != null) {
            return ResponseEntity.status(HttpStatus.OK).body(product);
        }
        else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @PostMapping("/create")
    public ResponseEntity<String> createProduct(
            @RequestBody @Valid ProductRequest product
    ){
        int result = productService.createProduct(product);
        if(result==0){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<String> updateProduct(
            @PathVariable Integer id,
            @RequestBody @Valid ProductRequest product
    ){
        if(productService.updateProduct(id,product)==0){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteProduct(
            @PathVariable Integer id
    ){
        if(productService.deleteProduct(id)==0){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
