package com.liznsalt.javatask.takeout.system.model.goods;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Builder;
import lombok.Data;
import lombok.ToString;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;

@Data
@ToString
public class OrderHistory implements Serializable {
    private static final long serialVersionUID = -7068862569939260013L;

    private Integer id;

    private Integer sid;

    private Integer cid;

    private Integer gid;

    private String shopName;

    private String goodsName;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone="GMT+8")
    private Date time;

    private String picture;

    private Integer num;

    private Double price;

    private Double total;

    private String state;

    private String consumerName;
}
