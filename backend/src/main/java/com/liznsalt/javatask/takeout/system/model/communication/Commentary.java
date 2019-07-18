package com.liznsalt.javatask.takeout.system.model.communication;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Builder;
import lombok.Data;
import lombok.ToString;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;

@Data
@ToString
public class Commentary implements Serializable {
    private static final long serialVersionUID = 3698009170147344245L;

    private Integer id;

    private Integer cid;

    private Integer sid;

    private String cname;

    private String sname;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone="GMT+8")
    private Date time;

    private String content;

    private int stars;
}
