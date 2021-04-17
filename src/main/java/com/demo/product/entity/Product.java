package com.demo.product.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Product {

    private long product_id;
    private String sellerId;
    private String title;
    private String manufacturer;
    private Price price;
    private boolean isLowQuantity;
    private boolean isSoldOut;
    private boolean isBackorder;
    private List<MetaField> metaFields;
    private boolean requiresShipping;
    private boolean isVisible;
    private DateField publishedAt;
    private DateField createdAt;
    private DateField updatedAt;
    private WorkFlow workFlow;
}
