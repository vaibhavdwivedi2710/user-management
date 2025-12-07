package com.app.user_management.dto.request;

import lombok.Data;

@Data
public class SignupRequest {

    private String email;
    private String password;
    private String fullName;
    private String phone;      // optional
    private String avatar;     // optional
    private String companyId;  // optional for normal customers

}
