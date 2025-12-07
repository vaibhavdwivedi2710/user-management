package com.app.user_management.controller;

import com.app.user_management.dto.request.SignupRequest;
import com.app.user_management.dto.response.ApiResponse;
import com.app.user_management.dto.response.SignupResponse;
import com.app.user_management.service.AuthService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    public final AuthService authService;
    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    //Register new user
    @PostMapping("/signup")
    public ResponseEntity<ApiResponse<SignupResponse>> signup(@RequestBody SignupRequest signupRequest) {
        SignupResponse signupResponse = authService.signup(signupRequest);
        // Check if the user already exists based on the response
        if (signupResponse.getFullName() == null) {
            return ResponseEntity.status(HttpStatus.CONFLICT)
                    .body(new ApiResponse<>(false, "User already exists", signupResponse));
        }
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new ApiResponse<>(true, "User signed up successfully", signupResponse));
    }

}
