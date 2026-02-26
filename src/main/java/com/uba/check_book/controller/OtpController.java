package com.uba.check_book.controller;

import com.uba.check_book.config.JwtService;
import com.uba.check_book.dto.OtpRequest;
import com.uba.check_book.security.UserPrincipal;
import com.uba.check_book.service.EntrustService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.Map;


@RestController
@RequestMapping("/otp")
@RequiredArgsConstructor
public class OtpController {

    private final EntrustService entrustService;
    private final JwtService jwtService;

    @PostMapping("/verify")
    public ResponseEntity<?> verifyOtp(@RequestBody OtpRequest request,
                                       @AuthenticationPrincipal UserPrincipal userPrincipal) throws Exception {

        String email = userPrincipal.getEmail();

        boolean otpValid = entrustService.sendEntrustToken(email, request.getOtp());

        if (!otpValid) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(Map.of("msg", "Otp not recognized or expired"));
        }

        String mainToken = jwtService.generateToken(userPrincipal.getId().toString(), "MAIN_TOKEN");

        return ResponseEntity.ok(Map.of(
                "token", mainToken
        ));
    }
}