package com.mirai.indidea.dto.Userdto;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class LoginDto {


    private String email;
    private String password;

    @NotBlank(message = "email不能为空")
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @NotBlank(message = "password不能为空")
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
