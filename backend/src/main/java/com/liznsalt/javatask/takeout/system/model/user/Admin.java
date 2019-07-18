package com.liznsalt.javatask.takeout.system.model.user;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

@Data
@ToString
public class Admin implements Serializable {
    private static final long serialVersionUID = -1643570031740269992L;

    private Integer id;

    private String username;

    @JsonIgnore
    private String password;
}
