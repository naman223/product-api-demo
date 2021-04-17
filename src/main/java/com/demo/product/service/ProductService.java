package com.demo.product.service;

import com.demo.product.dao.ProductDao;
import com.demo.product.entity.Product;
import com.demo.product.vo.PriceVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Service Class to handle the request.
 */
@Service
public class ProductService {

    @Autowired
    ProductDao productDao;

    public Product saveProduct(Product product) {
        return productDao.saveProduct(product);
    }

    public Product saveProductPrice(PriceVO priceVO) {
        Product productFound = productDao.saveProductPrice(priceVO);
        return productFound;
    }

    public PriceVO getProductPrice(Long productId) {
        PriceVO priceVO = productDao.getProductPrice(productId);
        return priceVO;
    }

    public Product getProduct(Long productId) {
        return productDao.getProduct(productId);
    }

    public Product updateProduct(Long productId, Product product) {
        Product productFound = productDao.updateProduct(productId,product);
        return productFound;
    }

    public boolean deleteProduct(Long productId) {
        return productDao.deleteProduct(productId);
    }

}
