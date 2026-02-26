package com.uba.check_book.controller;

import com.uba.check_book.dto.AuthResponse;
import com.uba.check_book.dto.AuthRequest;
import com.uba.check_book.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/signin")
    public ResponseEntity<AuthResponse> signin(@RequestBody AuthRequest request) throws Exception {
        String token = authService.signin(request.getEmail(), request.getPassword());

        AuthResponse response = new AuthResponse(
                "successful authentication",
                token
        );

        return ResponseEntity.ok(response);
    }
}