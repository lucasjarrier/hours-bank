package com.project.hourbank.util;

import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.stereotype.Service;

/**
 * DEIXAR FUNCIONAL NO FUTURO!!!
 *
 * Por enquanto essa classe está sem uso.
 */


@Service
public class Util {
    public boolean validatePassword(String password) {
        boolean status = true;

        if (password.length() < 6)
            status = false;
        return status;
    }

    public boolean verifyPassword(String userPassword, String knownPassword) {
        boolean status = false;
        String decryptedPassword = encrypt(knownPassword);
        if (userPassword.equals(decryptedPassword))
            status = true;
        return status;
    }

    public String encrypt(String senha) {
        return Base64.encodeBase64String(senha.getBytes());
    }
}
