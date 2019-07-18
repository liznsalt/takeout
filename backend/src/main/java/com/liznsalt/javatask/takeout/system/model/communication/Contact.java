package com.liznsalt.javatask.takeout.system.model.communication;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Builder;
import lombok.Data;
import lombok.ToString;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import	java.io.Serializable;
import java.util.Date;

@Data
@ToString
public class Contact implements Serializable {
    private static final long serialVersionUID = 8825018844858153935L;

    public static final String CONSUMER_SEND = "0";
    public static final String SHOP_SEND = "1";

    private Integer cid;

    private Integer sid;

    private String cname;

    private String sname;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone="GMT+8")
    private Date time;

    private String content;

    private String state;
}
