package com.uba.check_book;

import com.uba.check_book.util.CryptoUtil;
import org.springframework.beans.factory.annotation.Value;

public class TestCrypto {

    public static void main(String[] args) throws Exception {

        String secret = "1234567890123456";

        CryptoUtil crypto = new CryptoUtil(secret);

        String encrypted = crypto.encrypt("Password123");
        System.out.println("Encrypted: " + encrypted);

        String decrypted = crypto.decrypt(encrypted);
        System.out.println("Decrypted: " + decrypted);
    }
}