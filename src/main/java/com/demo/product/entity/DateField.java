package com.demo.product.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

@Data
public class DateField {

    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
    private Date $date;

    public DateField() {
    }

    public DateField(Date $date) {
        this.$date = $date;
    }

    public Date get$date() {
        return $date;
    }

    public void set$date(Date $date) {
        this.$date = $date;
    }
}
