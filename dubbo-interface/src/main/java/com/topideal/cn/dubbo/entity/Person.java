package com.topideal.cn.dubbo.entity;

import lombok.Data;
import lombok.ToString;

import javax.validation.constraints.*;
import java.io.Serializable;

@Data
@ToString
public class Person implements Serializable {

    private static final long serialVersionUID = 19708998612457201L;

    @Size(min = 1, max = 20)
    @NotNull(message = "name不能为空")
    public String name;

    @Min(0)
    @Max(100)
    public int age;

    @NotNull(message = "手机号不能为空")
    @Pattern(message="手机号不正确",regexp = "^1[0-9]{10}$")
    public String phone;

    @NotNull(message = "邮箱号不能为空")
    @Pattern(message="邮箱号不正确",regexp = "^[0-9]+@[a-z]+\\.[a-z]+$")
    public String email;

    @NotNull(message = "地址不能为空")
    @Size(min=1,max=100)
    public String address;
}
