package com.milypol.security.config;

import com.milypol.security.address.Address;
import com.milypol.security.address.AddressRepo;
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
    private final AddressRepo addressRepository;

    public DataInitializer(UserRepository userRepository, AddressRepo addressRepository) {
        this.userRepository = userRepository;
        this.addressRepository = addressRepository;
        this.passwordEncoder = new BCryptPasswordEncoder();
    }

    @Override
    public void run(String... args) throws Exception {
        if(userRepository.count() == 0) { // tworzymy tylko jeśli tabela jest pusta

            Address adminAddress = new Address();
            adminAddress.setCountry("Polska");
            adminAddress.setCity("Dąbrowa Górnicza");
            adminAddress.setStreet("ul. Przykładowa");
            adminAddress.setNumber("10");
            adminAddress.setPostalCode("41-300");

            Address magazynierAddress = new Address();
            magazynierAddress.setCountry("Polska");
            magazynierAddress.setCity("Dąbrowa Górnicza");
            magazynierAddress.setStreet("ul. Testowa");
            magazynierAddress.setNumber("5");
            magazynierAddress.setPostalCode("41-300");

            Address kierowcaAddress = new Address();
            kierowcaAddress.setCountry("Polska");
            kierowcaAddress.setCity("Dąbrowa Górnicza");
            kierowcaAddress.setStreet("ul. Statyczna");
            kierowcaAddress.setNumber("7");
            kierowcaAddress.setPostalCode("41-300");

            User admin = new User();
            admin.setAddress(adminAddress);
            admin.setDescription("Administrator systemu");
            admin.setEmail("milypol98@gmail.com");
            admin.setFirstname("Jan");
            admin.setLastName("Kowalski");
            admin.setPassword(passwordEncoder.encode("123456"));
            admin.setRole(Role.ADMIN);

            User magazynier = new User();
            magazynier.setAddress(magazynierAddress);
            magazynier.setDescription("Magazynier");
            magazynier.setEmail("milypol1998@gmail.com");
            magazynier.setFirstname("Anna");
            magazynier.setLastName("Nowak");
            magazynier.setPassword(passwordEncoder.encode("123456"));
            magazynier.setRole(Role.USER);

            User kierowca = new User();
            kierowca.setAddress(kierowcaAddress);
            kierowca.setDescription("Kierowca serwisowy");
            kierowca.setEmail("kierowca@example.com");
            kierowca.setFirstname("Piotr");
            kierowca.setLastName("Wiśniewski");
            kierowca.setPassword(passwordEncoder.encode("haslo789"));
            kierowca.setRole(Role.USER);

// Najpierw zapisujesz adresy
            addressRepository.save(adminAddress);
            addressRepository.save(magazynierAddress);
            addressRepository.save(kierowcaAddress);

// Następnie przypisujesz je do użytkowników i zapisujesz użytkowników
            admin.setAddress(adminAddress);
            magazynier.setAddress(magazynierAddress);
            kierowca.setAddress(kierowcaAddress);

            userRepository.save(admin);
            userRepository.save(magazynier);
            userRepository.save(kierowca);
        }
    }
}