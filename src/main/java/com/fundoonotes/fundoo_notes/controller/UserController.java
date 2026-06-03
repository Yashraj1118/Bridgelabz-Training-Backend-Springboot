package com.fundoonotes.fundoo_notes.controller;

import com.fundoonotes.fundoo_notes.dto.*;
import com.fundoonotes.fundoo_notes.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    // REGISTER
    @PostMapping("/register")
    public ResponseEntity<ApiResponse> register(
            @Valid @RequestBody UserDTO dto) {
        try {
            String message = userService.register(dto);
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(new ApiResponse(201, message));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new ApiResponse(400, e.getMessage()));
        }
    }

    // VERIFY EMAIL WITH OTP (NEW)
    @PostMapping("/verify-otp")
    public ResponseEntity<ApiResponse> verifyOtp(
            @Valid @RequestBody VerifyOtpDTO dto) {
        try {
            String message = userService.verifyOtp(dto);
            return ResponseEntity.ok(new ApiResponse(200, message));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new ApiResponse(400, e.getMessage()));
        }
    }

    // RESEND OTP (NEW)
    @PostMapping("/resend-otp")
    public ResponseEntity<ApiResponse> resendOtp(
            @RequestParam String email) {
        try {
            String message = userService.resendOtp(email);
            return ResponseEntity.ok(new ApiResponse(200, message));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new ApiResponse(400, e.getMessage()));
        }
    }

    // VERIFY EMAIL WITH TOKEN (OLD - keep for backward compatibility)
    @GetMapping("/verify")
    public ResponseEntity<ApiResponse> verifyEmail(
            @RequestParam String token) {
        try {
            String message = userService.verifyEmail(token);
            return ResponseEntity.ok(new ApiResponse(200, message));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new ApiResponse(400, e.getMessage()));
        }
    }

    // LOGIN
    @PostMapping("/login")
    public ResponseEntity<ApiResponse> login(
            @Valid @RequestBody LoginDTO dto) {
        try {
            String token = userService.login(dto);
            return ResponseEntity.ok()
                    .header("Authorization", "Bearer " + token)
                    .body(new ApiResponse(200, "Login successful"));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new ApiResponse(400, e.getMessage()));
        }
    }

    // FORGOT PASSWORD WITH OTP (NEW)
    @PostMapping("/forgot-password-otp")
    public ResponseEntity<ApiResponse> forgotPasswordOtp(
            @RequestParam String email) {
        try {
            String message = userService.forgotPasswordOtp(email);
            return ResponseEntity.ok(new ApiResponse(200, message));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new ApiResponse(400, e.getMessage()));
        }
    }

    // RESET PASSWORD WITH OTP (NEW)
    @PostMapping("/reset-password-otp")
    public ResponseEntity<ApiResponse> resetPasswordWithOtp(
            @Valid @RequestBody ResetPasswordOtpDTO dto) {
        try {
            String message = userService.resetPasswordWithOtp(dto);
            return ResponseEntity.ok(new ApiResponse(200, message));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new ApiResponse(400, e.getMessage()));
        }
    }

    // FORGOT PASSWORD WITH LINK (OLD - keep)
    @PostMapping("/forgot-password")
    public ResponseEntity<ApiResponse> forgotPassword(
            @RequestParam String email) {
        try {
            String message = userService.forgotPassword(email);
            return ResponseEntity.ok(new ApiResponse(200, message));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new ApiResponse(400, e.getMessage()));
        }
    }

    // RESET PASSWORD WITH TOKEN (OLD - keep)
    @PostMapping("/reset-password")
    public ResponseEntity<ApiResponse> resetPassword(
            @RequestParam String token,
            @RequestParam String newPassword) {
        try {
            String message = userService.resetPassword(
                    token, newPassword);
            return ResponseEntity.ok(new ApiResponse(200, message));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new ApiResponse(400, e.getMessage()));
        }
    }
}