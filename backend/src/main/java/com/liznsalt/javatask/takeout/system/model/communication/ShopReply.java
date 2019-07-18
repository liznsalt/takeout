package com.liznsalt.javatask.takeout.system.model.communication;
import	java.io.DataOutput;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.ToString;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;

@Data
@ToString
public class ShopReply implements Serializable{
    private static final long serialVersionUID = -4158449852262184420L;

    private Integer sid; // shop

    private Integer coid; // commentary

    private String sname;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone="GMT+8")
    private Date time;

    private String content;
}
