package com.milypol.security.user;

import java.util.List;

public interface UserService {
    List<User> getAllUsers();
    User getUserById(Integer id);
    User getUserByEmail(String email);
    User saveUser(User user);
    void deleteUser(Integer id);
}
