package com.jnshu.service1.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.jnshu.dao.ProductMapper1;
import com.jnshu.dto1.ProductListRPO;
import com.jnshu.entity.Product;
import com.jnshu.service1.ProductService1;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * product相关service
 * @author wangqichao
 */
@Service
//开启事务
@Transactional
public class ProductServiceImpl1 implements ProductService1 {
    @Autowired
    ProductMapper1 productMapper1;

    private static final Logger log= LoggerFactory.getLogger(ProductService1.class);
    /**
     * 获得产品分页列表
     * @param rpo 产品分页条件
     * @return 产品列表
     */
    @Override
    public Page<Product> getProductList(ProductListRPO rpo) {
        Page<Product> products= PageHelper.startPage(rpo.getPage(),rpo.getSize());
        productMapper1.getProductListByRpo(rpo);
        return products;
    }

    /**
     * 获得指定产品详情
     * @param id 产品id
     * @return 产品详情
     */
    @Override
    public Product getProductById(long id) {
        return productMapper1.getProductById(id);
    }

    /**
     * 新增产品
     * @param product 新增产品内容
     * @return 操作结果
     */
    @Override
    public int addProduct(Product product) {
        product.setCreateAt(System.currentTimeMillis());
        int a= productMapper1.addProduct(product);
        long id=product.getId();
        log.info("新增产品id为"+id);
        return a;
    }

    /**
     * 修改产品信息
     * @param product 需变为的产品信息
     * @return 修改结果
     */
    @Override
    public int updateProduct(Product product) {
        product.setUpdateAt(System.currentTimeMillis());
        return productMapper1.updateProduct(product);
    }

    /**
     * 修改产品在售状态
     * @param product 需变为的产品信息
     * @return 修改结果
     */
    @Override
    public int updateProductStatus(Product product) {
        product.setUpdateAt(System.currentTimeMillis());
        return productMapper1.updateProductStatus(product);
    }
}
