package com.milypol.security.user;

import jakarta.validation.Valid;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;

public interface UserService {
    User getUserById(Integer id);
    User save(User user);
    void register(RegistrationRequest request);
    List<User> getAllUsers();
    User getByEmail(String email);
    UpdateRequest getUserForUpdate(Integer id);
    UpdateAdminRequest getUserForAdminUpdate(Integer id);
    void updateUserByAdmin(Integer id, @Valid UpdateAdminRequest request);
    void updateUserProfile(String username, @Valid UpdateRequest request);
}



