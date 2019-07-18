package com.liznsalt.javatask.takeout.system.model.goods;

import lombok.Builder;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.util.Date;

@Data
@ToString
public class ShoppingCart implements Serializable {
    private static final long serialVersionUID = 694115364914409320L;

    private Integer cid;

    private Integer sid;

    private Integer gid;

    private String shopName;

    private String goodsName;

    private String picture;

    private Integer num;

    private Double price;

    private Double total;
}
