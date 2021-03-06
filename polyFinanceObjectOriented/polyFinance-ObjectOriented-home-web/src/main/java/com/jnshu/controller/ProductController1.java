package com.jnshu.controller;

import com.jnshu.dto1.ProductListRPO;
import com.jnshu.entity.Product;
import com.jnshu.exception.MyException;
import com.jnshu.service1.ProductService1;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 产品管理相关模块,2E
 * @author wangqichao
 */
@RestController
public class ProductController1 {

    private static final Logger log= LoggerFactory.getLogger(ProductController1.class);

    @Autowired
    ProductService1 productService1;

    /**
     * 获得产品列表
     * @param rpo 是否是推荐产品，区别推荐页
     * @return 返回参数，code,message，产品列表
     */
    @GetMapping(value = "/a/product/list")
    public Map getProductList(ProductListRPO rpo)throws Exception{
        log.info("获得产品列表，1代表推荐页，没有代表全部，条件为："+rpo.getIsRecommend());
        List<Product> products= productService1.getProductList(rpo);
        Map<String,Object> map=new HashMap<>();
        map.put("code",0);
        map.put("message","success");
        map.put("data",products);
        return map;
    }

    /**
     * 获得指定产品详情
     * @param id 产品id
     * @return 返回参数，code,message,产品详情
     */
    @GetMapping(value = "/a/product/{id}")
    public Map getProduct(@PathVariable(value = "id")long id)throws Exception{
        log.info("获得id为"+id+"的产品");
        Map<String,Object> map=new HashMap<>();
        Product product= productService1.getProductById(id);
        map.put("code",0);
        map.put("message","success");
        map.put("data",product);
        return map;
    }
}
