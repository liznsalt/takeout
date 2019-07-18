package com.liznsalt.javatask.takeout.system.model.goods;

import lombok.Builder;
import lombok.Data;
import lombok.ToString;
import org.hibernate.validator.constraints.Length;
import org.springframework.data.annotation.Id;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import	java.io.Serializable;

@Data
@ToString
public class Goods implements Serializable {
    private static final long serialVersionUID = 8745947620292115187L;

    private Integer id;
    private String name;
    private Double price;
    private Integer stock;
    private String type;
    private Integer buyCount;
    private String pictureUrl;
    private Double praise;
    private String desc;
    private Integer sid;
}
