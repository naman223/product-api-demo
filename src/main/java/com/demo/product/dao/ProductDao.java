package com.demo.product.dao;

import com.demo.product.controller.ProductController;
import com.demo.product.entity.DateField;
import com.demo.product.entity.Product;
import com.demo.product.vo.PriceVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;

/**
 * Product Data Access Object Class.
 * This class is just storing the product data.
 * It can be replaced with Repository in future to persist data permanently.
 */
@Component
public class ProductDao {

    private static final Logger log = LoggerFactory.getLogger(ProductController.class);
    HashMap<Long, Product> productData = new HashMap<>();

    /**
     * Creates the Product Object
     * @param product
     * @return
     */
    public Product saveProduct(Product product) {
        log.info("saveProduct: Saving product {} ",product.toString());
        DateField dateField = new DateField();
        dateField.set$date(new Date());
        //Adding current date for both publish and create as couldn't able to figure
        //out the difference as of now.
        product.setPublishedAt(dateField);
        product.setCreatedAt(dateField);
        productData.put(product.getProduct_id(),product);
        return product;
    }

    /**
     * Get the Product Object based on product Id.
     * @param productId
     * @return
     */
    public Product getProduct(Long productId) {
        Product product = productData.get(productId);
        if(product!=null) {
            log.info("getProduct: Product having product Id {} found.",productId);
            return product;
        } else {
            log.error("getProduct: Product having product Id {} not found.",productId);
            return null;
        }
    }

    /**
     * Delete the Product Object based on product Id.
     * @param productId
     * @return
     */
    public boolean deleteProduct(Long productId) {
        Product product = getProduct(productId);
        if(product!=null) {
            log.info("deleteProduct: Product having product Id {} found.",productId);
            productData.remove(productId);
            return true;
        }
        log.error("deleteProduct: Product having product Id {} not found.",productId);
        return false;
    }

    /**
     * Updates the Product Object based on product Id.
     * @param productId
     * @param product
     * @return
     */
    public Product updateProduct(Long productId, Product product) {
        Product foundProduct = productData.get(productId);
        if(foundProduct!=null) {
            log.info("updateProduct: Product having product Id {} found.",productId);
            product.setProduct_id(productId);
            DateField dateField = new DateField();
            dateField.set$date(new Date());
            //Updating current date time when someone change the Product details
            product.setUpdatedAt(dateField);
            log.info("updateProduct: Product updated {}",product.toString());
            productData.put(productId,product);
            return product;
        }
        log.error("updateProduct: Product having product Id {} not found.",productId);
        return null;
    }

    /**
     * Creates the Price for given Product Id.
     * @param priceVO
     * @return
     */
    public Product saveProductPrice(PriceVO priceVO) {
        Product foundProduct = productData.get(priceVO.getProduct_id());
        if(foundProduct!=null) {
            log.info("saveProductPrice: Product having product Id {} found.",priceVO.getProduct_id());
            DateField dateField = new DateField();
            dateField.set$date(new Date());
            foundProduct.setUpdatedAt(dateField);
            foundProduct.setPrice(priceVO.getPrice());
            log.info("saveProductPrice: Product Price Saved {}",priceVO.toString());
            productData.put(priceVO.getProduct_id(),foundProduct);
            return foundProduct;
        }
        log.error("saveProductPrice: Product having product Id {} not found.",priceVO.getProduct_id());
        return null;
    }

    /**
     * Get the price for given Prodcut Id.
     * @param productId
     * @return
     */
    public PriceVO getProductPrice(Long productId) {
        Product foundProduct = productData.get(productId);
        log.info("getProductPrice: Product having product Id {} found.",productId);
        if(foundProduct!=null) {
            PriceVO priceVO = new PriceVO();
            priceVO.setProduct_id(productId);
            priceVO.setPrice(foundProduct.getPrice());
            log.info("getProductPrice: Product Price {}",priceVO.toString());
            return priceVO;
        }
        log.error("getProductPrice: Product having product Id {} not found.",productId);
        return null;
    }
}
