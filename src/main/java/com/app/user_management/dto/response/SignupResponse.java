package com.app.user_management.dto.response;

import com.app.user_management.entity.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SignupResponse {
    private String fullName;
    private String email;
    private String phone;
    private User.Role role;
    private String companyId;
    private User.Status status;
}
