package com.milypol.security.user;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RegistrationService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public void register(RegistrationRequest request) {

        userRepository.findByEmail(request.getEmail()).ifPresent(u -> {
            throw new RuntimeException("Użytkownik o takiej skrzynce e-mail już istnieje");
        });

        if (!request.getPassword().equals(request.getConfirmPassword())) {
            throw new RuntimeException("Hasła się nie zgadzają");
        }

        User user = new User();
        user.setEmail(request.getEmail());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setRole(Role.USER);

        userRepository.save(user);
    }
}
