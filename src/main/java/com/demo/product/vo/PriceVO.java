package com.demo.product.vo;

import com.demo.product.entity.Price;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PriceVO {
    private Long product_id;
    private Price price;
}
