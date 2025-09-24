package com.milypol.security.user;

import com.milypol.security.address.Address;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Transactional(readOnly = true)
    @Override
    public User getUserById(Integer id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Nie znaleziono użytkownika o id " + id));
    }

    @Transactional(readOnly = true)
    @Override
    public User getByEmail(String email) {
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new IllegalArgumentException("Nie znaleziono użytkownika o email " + email));
    }

    @Transactional
    @Override
    public User save(User user) {
        return userRepository.save(user);
    }

    @Transactional
    @Override
    public void register(RegistrationRequest request) {

        userRepository.findByEmail(request.getEmail()).ifPresent(u -> {
            throw new RuntimeException("Użytkownik o takiej skrzynce e-mail już istnieje");
        });

        if (!request.getPassword().equals(request.getConfirmPassword())) {
            throw new RuntimeException("Hasła się nie zgadzają");
        }

        User user = new User();
        user.setFirstname(request.getFirstname());
        user.setLastName(request.getLastName());
        user.setEmail(request.getEmail());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setRole(Role.ROLE_USER);

        userRepository.save(user);
    }

    @Transactional(readOnly = true)
    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }
    @Transactional(readOnly = true)
    public UpdateRequest getUserForUpdate(Integer id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Nie znaleziono użytkownika o ID: " + id));

        UpdateRequest dto = new UpdateRequest();

        dto.setId(user.getId());
        dto.setFirstname(user.getFirstname());
        dto.setLastName(user.getLastName());
        dto.setPhone(user.getPhone());
        dto.setDescription(user.getDescription());

        if (user.getAddress() == null) {
            dto.setAddress(new Address());
        } else {
            dto.setAddress(user.getAddress());
        }
        return dto;
    }
    @Transactional(readOnly = true)
    public UpdateAdminRequest getUserForAdminUpdate(Integer id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Nie znaleziono użytkownika o ID: " + id));

        UpdateAdminRequest dto = new UpdateAdminRequest();

        dto.setId(user.getId());
        dto.setFirstname(user.getFirstname());
        dto.setLastName(user.getLastName());
        dto.setEmail(user.getEmail());
        dto.setPhone(user.getPhone());
        dto.setDescription(user.getDescription());
        dto.setRole(user.getRole());

        if (user.getAddress() == null) {
            dto.setAddress(new Address());
        } else {
            dto.setAddress(user.getAddress());
        }
        return dto;
    }
    /**
     * Metoda do aktualizacji WŁASNEGO profilu przez zalogowanego użytkownika.
     * Wymaga weryfikacji aktualnego hasła, jeśli użytkownik chce je zmienić.
     * Nie pozwala na zmianę roli.
     *
     * @param userEmail Email zalogowanego użytkownika (z kontekstu bezpieczeństwa).
     * @param request DTO z danymi do aktualizacji.
     */
// W pliku UserServiceImpl.java

    @Transactional
    public void updateUserProfile(String userEmail, UpdateRequest request) {
        User user = userRepository.findByEmail(userEmail)
                .orElseThrow(() -> new RuntimeException("Użytkownik nie znaleziony."));

        user.setFirstname(request.getFirstname());
        user.setLastName(request.getLastName());
        user.setPhone(request.getPhone());
        user.setDescription(request.getDescription());
        user.setAddress(request.getAddress());

        if (StringUtils.hasText(request.getNewPassword())) {
            if (!passwordEncoder.matches(request.getCurrentPassword(), user.getPassword())) {
                // Rzucamy wyjątek, który zostanie złapany w kontrolerze
                throw new IllegalArgumentException("Aktualne hasło jest nieprawidłowe.");
            }

            // Jeśli wszystko się zgadza, kodujemy i ustawiamy nowe hasło.
            user.setPassword(passwordEncoder.encode(request.getNewPassword()));
        }

        // Zapisujemy zmiany (dane profilowe i/lub nowe hasło)
        userRepository.save(user);
    }

    /**
     * Metoda do aktualizacji DOWOLNEGO profilu przez ADMINISTRATORA.
     * Nie wymaga weryfikacji hasła.
     * Pozwala na zmianę roli.
     *
     * @param userId ID użytkownika, który ma być zaktualizowany.
     * @param request DTO z danymi do aktualizacji od admina.
     */
    @Transactional
    public void updateUserByAdmin(Integer userId, UpdateAdminRequest request) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("Użytkownik o ID " + userId + " nie istnieje."));

        user.setFirstname(request.getFirstname());
        user.setLastName(request.getLastName());
        user.setEmail(request.getEmail());
        user.setPhone(request.getPhone());
        user.setDescription(request.getDescription());
        user.setAddress(request.getAddress());
        user.setRole(request.getRole());

        if (StringUtils.hasText(request.getNewPassword())) {
            user.setPassword(passwordEncoder.encode(request.getNewPassword()));
        }
        userRepository.save(user);
    }
}
