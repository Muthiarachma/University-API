package com.example.universitas.model.dto;

import lombok.Data;

@Data
public class UserDTO {
    private String email;
    private String password;
    private String firstname;
    private String lastname;
    private int statusCode;
    private String status;
}
