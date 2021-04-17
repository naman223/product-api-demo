package com.demo.product.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import javax.persistence.Entity;

@Component
@Data
@AllArgsConstructor
@NoArgsConstructor
public class WorkFlow {
    private String status;
}
