package com.uba.check_book.service;

import com.uba.check_book.config.JwtService;
import com.uba.check_book.entity.User;
import com.uba.check_book.repository.UserRepository;
import com.uba.check_book.util.CryptoUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final ActiveDirectoryService adService;
    private final JwtService jwtService;
    private final EntrustService entrustService;
    private final CryptoUtil cryptoUtil;

    private static final int MAX_LOGIN_ATTEMPT = 3;

    public String signin(String email, String encryptedPassword) throws Exception {

        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Account not found"));

        if (user.isLocked())
            throw new RuntimeException("Account locked");

        String clearPassword = cryptoUtil.decrypt(encryptedPassword);

        boolean canLogin = adService.login(email, clearPassword);

        if (!canLogin) {
            user.incrementLoginAttempt();
            userRepository.save(user);
            throw new RuntimeException("Invalid credentials");
        }

        user.resetLoginAttempt();
        userRepository.save(user);

        return jwtService.generateToken(
                String.valueOf(user.getId()),
                "PASSWORD_TOKEN"
        );
    }
    // Vérification OTP
    public boolean verifyOtp(String email, String otp) throws Exception {
        return entrustService.sendEntrustToken(email, otp);
    }
}
