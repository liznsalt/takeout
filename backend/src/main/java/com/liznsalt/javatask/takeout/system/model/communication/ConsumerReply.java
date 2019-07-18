package com.liznsalt.javatask.takeout.system.model.communication;

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
public class ConsumerReply implements Serializable {
    private static final long serialVersionUID = -1187657191657335995L;

    private Integer cid; // consumer

    private Integer coid; // commentary

    private String cname;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone="GMT+8")
    private Date time;

    private String content;
}
