package com.liznsalt.javatask.takeout.system.model.user;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonView;
import lombok.Builder;
import lombok.Data;
import lombok.ToString;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.validation.constraints.Min;
import java.io.Serializable;

@Data
@ToString
public class Consumer implements User, Serializable {
    private static final long serialVersionUID = 3502095783428429745L;

    public interface UserView { }

    public interface AdminView extends UserView { }

    @JsonView(UserView.class)
    private Integer id;

    @JsonView(UserView.class)
    private String username;

    @JsonView(AdminView.class)
    private String password;

    @JsonView(UserView.class)
    private String telephone;

    @JsonView(UserView.class)
    private String email;

    @JsonView(UserView.class)
    private String sex;

    @JsonView(UserView.class)
    private Integer age;

    @JsonView(UserView.class)
    private String address;

    @JsonView(UserView.class)
    private Double balance;
}
