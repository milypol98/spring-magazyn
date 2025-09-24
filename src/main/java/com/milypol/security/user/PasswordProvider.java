package com.milypol.security.user;

public interface PasswordProvider {
    String getNewPassword();
    String getConfirmPassword();

    default String getCurrentPassword() {
        return null;
    }
}
