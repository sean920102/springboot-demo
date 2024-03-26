package com.example.demo2.dao;

import com.example.demo2.constant.ProductCategory;
import com.example.demo2.dao.Interface.IProduct;
import com.example.demo2.dto.ProductRequest;
import com.example.demo2.dto.ProductRequestParams;
import com.example.demo2.mapper.ProductRowMapper;
import com.example.demo2.modal.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class ProductDao implements IProduct {
    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    private String addFilter(String sql,ProductRequestParams req,Map<String,Object>map){
        if(req.getProductCategory() != null){
            sql += " AND category = :category";
            map.put("category",req.getProductCategory().name());
            System.out.println(map.get("category"));
        }
        if(req.getSearch() != null){
            sql+=" AND product_name like :search";
            map.put("search","%"+req.getSearch()+"%");
        }
        sql += " order by "+req.getOrderby()+" "+req.getSort();
        sql += " limit "+req.getLimit()+" offset "+req.getOffset();
        System.out.println(map.get("category"));

        return sql;
    }
    @Override
    public List<Product> getProduct(ProductRequestParams req) {
        String sql = "select * from product where 1=1";

        Map<String,Object> map = new HashMap<>();
        sql = addFilter(sql,req,map);
        map.forEach((key,val) -> System.out.println(key+" : "+val));
        System.out.println(sql);
        return namedParameterJdbcTemplate.query(sql,map,new ProductRowMapper());
    }

    @Override
    public Integer deleteProduct(Integer id) {
        String sql = "DELETE FROM product where product_id = :id";
        Map<String,Object> map = new HashMap<>();
        map.put("id",id);
        return namedParameterJdbcTemplate.update(sql,new MapSqlParameterSource(map));
    }

    public Product getProductById(Integer id) {
        String sql ="select * from product where product_id = :product_id";
        Map<String,Object> map = new HashMap<>();
        map.put("product_id",id);
        List<Product> productList =
        namedParameterJdbcTemplate.query(sql,map,new ProductRowMapper());
        if (productList.size()==0){
            return null;
        }
        return productList.get(0);
    }

    public Integer createProduct(ProductRequest product){
        String sql =
                "INSERT INTO product (product_name,category,image_url,price,stock,description,created_date,last_modified_date)" +
                " VALUES (:product_name,:category,:image_url,:price,:stock,:description,:created_date,:last_modified_date)";
        Map<String,Object> map = new HashMap<>();
        map.put("product_name",product.getProduct_name());
        map.put("category",product.getCategory().toString());
        map.put("image_url",product.getImage_url());
        map.put("price",product.getPrice());
        map.put("stock",product.getStock());
        map.put("description",product.getDescription());
        Date date = new Date();
        map.put("created_date",date);
        map.put("last_modified_date",date);
        KeyHolder keyHolder = new GeneratedKeyHolder();
        System.out.println("123");
        return namedParameterJdbcTemplate.update(sql,new MapSqlParameterSource(map),keyHolder);
    }

    @Override
    public Integer updateProduct(Integer id,ProductRequest productRequest) {
        String sql ="UPDATE product set product_name=:product_name," +
                "category=:category," +
                "image_url=:image_url," +
                "price=:price," +
                "stock=:stock," +
                "description=:description," +
                "last_modified_date=:last_modified_date" +
                " where product_id = :product_id";
        Map <String,Object> map = new HashMap<>();
        map.put("product_name",productRequest.getProduct_name());
        map.put("category",productRequest.getCategory().toString());
        map.put("image_url",productRequest.getImage_url());
        map.put("price",productRequest.getPrice());
        map.put("stock",productRequest.getStock());
        map.put("description",productRequest.getDescription());
        Date date = new Date();
        map.put("last_modified_date",date);
        map.put("product_id",id);
        int result = namedParameterJdbcTemplate.update(sql,new MapSqlParameterSource(map));
        System.out.println(result);
        return result;
    }

    @Override
    public Integer countProduct(ProductRequestParams req) {
        String sql = "select count(*) from product where 1=1";

        Map<String,Object> map = new HashMap<>();
        sql = addFilter(sql,req,map);

        map.forEach((key,val) -> {
            System.out.println("1=>Key:"+key+", Val:"+val);
        });

        System.out.println("1");
        return namedParameterJdbcTemplate.queryForObject(sql,map,Integer.class);
    }
}
