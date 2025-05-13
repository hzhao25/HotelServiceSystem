package com.sky.dto;

import lombok.Data;

import java.io.Serializable;
@Data
public class ForgetPasswordDTO implements Serializable {

    private String newPassword;

    private String oldPassword;

    private String phone;
}
