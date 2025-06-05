package com.milypol.security.config;

import com.milypol.security.user.Role;
import com.milypol.security.user.User;
import com.milypol.security.user.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class DataInitializer implements CommandLineRunner {

    private final UserRepository userRepository; // Twój repozytorium do userów
    private final BCryptPasswordEncoder passwordEncoder;

    public DataInitializer(UserRepository userRepository) {
        this.userRepository = userRepository;
        this.passwordEncoder = new BCryptPasswordEncoder();
    }

    @Override
    public void run(String... args) throws Exception {
        if(userRepository.count() == 0) { // tworzymy tylko jeśli tabela jest pusta

            User admin = new User();
            admin.setAddress("ul. Przykładowa 10, 41-300 Dąbrowa Górnicza");
            admin.setDescription("Administrator systemu");
            admin.setEmail("milypol98@gmail.com");
            admin.setFirstname("Jan");
            admin.setLastName("Kowalski");
            admin.setPassword(passwordEncoder.encode("123456")); // hashujemy hasło
            admin.setRole(Role.ADMIN);

            User magazynier = new User();
            magazynier.setAddress("ul. Testowa 5, 41-300 Dąbrowa Górnicza");
            magazynier.setDescription("Magazynier");
            magazynier.setEmail("milypol1998@gmail.com");
            magazynier.setFirstname("Anna");
            magazynier.setLastName("Nowak");
            magazynier.setPassword(passwordEncoder.encode("123456"));
            magazynier.setRole(Role.USER);

            User kierowca = new User();
            kierowca.setAddress("ul. Statyczna 7, 41-300 Dąbrowa Górnicza");
            kierowca.setDescription("Kierowca serwisowy");
            kierowca.setEmail("kierowca@example.com");
            kierowca.setFirstname("Piotr");
            kierowca.setLastName("Wiśniewski");
            kierowca.setPassword(passwordEncoder.encode("haslo789"));
            kierowca.setRole(Role.USER);

            userRepository.save(admin);
            userRepository.save(magazynier);
            userRepository.save(kierowca);
        }
    }
}