package com.demo.product.controller;

import com.demo.product.entity.Price;
import com.demo.product.entity.Product;
import com.demo.product.service.ProductService;
import com.demo.product.vo.PriceVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController("/")
public class ProductController {

    @Autowired
    ProductService productService;
    private static final Logger log = LoggerFactory.getLogger(ProductController.class);

    /**
     * Save Product
     * @param product
     * @return
     */
    @PostMapping("/product")
    public ResponseEntity<Product> createProduct(@RequestBody Product product) {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json");
        boolean validate = validatePostInput(product);
        if (!validate) {
            log.error("Missing values in product");
            return new ResponseEntity<>(headers, HttpStatus.BAD_REQUEST);
        } else {
            Product productSaved = productService.saveProduct(product);
            log.info("Product Saved");
            return new ResponseEntity<>(productSaved, headers, HttpStatus.OK);
        }
    }

    /**
     * Save Product Price.
     * @param priceVO
     * @return
     */
    @PostMapping("/product/price")
    public ResponseEntity<Product> saveProductPrice(@RequestBody PriceVO priceVO) {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json");
        boolean validate = validatePriceInput(priceVO);
        if (!validate) {
            log.info("Missing price details");
            return new ResponseEntity<>(headers, HttpStatus.BAD_REQUEST);
        } else {
            log.info("Updating the price for Product having id {}",priceVO.getProduct_id());
            Product productSaved = productService.saveProductPrice(priceVO);
            if (productSaved != null) {
                log.info("Updated product price {}",productSaved.toString());
                return new ResponseEntity<>(productSaved, headers, HttpStatus.OK);
            } else {
                log.error("Product not found to update having product id {}", priceVO.getProduct_id());
                return new ResponseEntity<>(headers, HttpStatus.NOT_FOUND);
            }
        }
    }

    /**
     * Get Product Price.
     * @param productId
     * @return
     */
    @GetMapping("/product/price/{productId}")
    public ResponseEntity<PriceVO> getProductPrice(@PathVariable("productId") Long productId) {
        log.info("Getting the Product Price");
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json");

        PriceVO priceVO = productService.getProductPrice(productId);
        if (priceVO != null) {
            return new ResponseEntity<>(priceVO, headers, HttpStatus.OK);
        } else {
            log.error("Product not found having product id {}", productId);
            return new ResponseEntity<>(headers, HttpStatus.NOT_FOUND);
        }

    }

    /**
     * Get Product.
     * @param productId
     * @return
     */
    @GetMapping("/product/{productId}")
    public ResponseEntity<Product> getProduct(@PathVariable("productId") Long productId) {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json");
        log.info("Getting the Product having product id {}", productId);
        Product product = productService.getProduct(productId);
        if (product != null) {
            log.info("Found the Product {}}", product.toString());
            return new ResponseEntity<>(product, headers, HttpStatus.OK);
        } else {
            log.error("Product not found having product id {}", productId);
            return new ResponseEntity<>(headers, HttpStatus.NOT_FOUND);
        }
    }

    /**
     * Update existing Product.
     * @param productId
     * @param product
     * @return
     */
    @PutMapping("/product/{productId}")
    public ResponseEntity<Product> updateProduct(@PathVariable("productId") Long productId, @RequestBody Product product) {
        log.info("Updating the Product having product id {}", productId);
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json");
        boolean validate = validatePutInput(product);
        if (!validate) {
            log.error("Missing values in product");
            return new ResponseEntity<>(headers, HttpStatus.BAD_REQUEST);
        }
        Product updatedProduct = productService.updateProduct(productId, product);
        if (updatedProduct != null) {
            log.info("Updated the Product having product id {}", productId);
            return new ResponseEntity<>(updatedProduct, headers, HttpStatus.OK);
        } else {
            log.error("Product not found having product id {}", productId);
            return new ResponseEntity<>(headers, HttpStatus.NOT_FOUND);
        }
    }

    /**
     * Delete Product
     * @param productId
     * @return
     */
    @DeleteMapping("/product/{productId}")
    public ResponseEntity<Void> deleteProduct(@PathVariable("productId") Long productId) {
        log.info("Deleting the Product having product id {}", productId);
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json");
        boolean deleted = productService.deleteProduct(productId);
        if (deleted) {
            log.info("Deleted the Product having product id {}", productId);
            return new ResponseEntity<>(headers, HttpStatus.OK);
        } else {
            log.error("Product not found having product id {}", productId);
            return new ResponseEntity<>(headers, HttpStatus.NOT_FOUND);
        }
    }

    /**
     * Validating product object during Saving new Product.
     * Assumption - Product Id and Product title is must for any Product.
     * TODO - We can autogenerate product Id
     * @param product
     * @return
     */
    private boolean validatePostInput(Product product) {
        if (product == null) {
            return false;
        } else if (product.getProduct_id() == 0l ||
                product.getTitle().trim() == "") {
            return false;
        }
        return true;
    }

    /**
     * Validating product object during Updating Product.
     * @param product
     * @return
     */
    private boolean validatePutInput(Product product) {
        if (product == null)
            return false;
        return true;
    }

    /**
     * Validating price object.
     * Assumption - Price id and Price object should not be empty.
     * @param priceVO
     * @return
     */
    private boolean validatePriceInput(PriceVO priceVO) {
        if (priceVO == null) {
            return false;
        } else if (priceVO.getProduct_id() == null || priceVO.getProduct_id() == 0l ||
                priceVO.getPrice() == null) {
            return false;
        }
        return true;
    }
}
