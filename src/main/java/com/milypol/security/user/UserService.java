package com.milypol.security.user;

import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;

public interface UserService {
    User getUserById(Integer id);
    User save(User user);
    User updateUser(Integer id, User form, String newPassword, PasswordEncoder passwordEncoder);
    List<User> getAllUsers();

    // NOWE: pobranie użytkownika po email/username
    User getByEmail(String email);
}
