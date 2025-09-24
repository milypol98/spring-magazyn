package com.milypol.security.config;

import com.milypol.security.address.Address;
import com.milypol.security.user.Role;
import com.milypol.security.user.User;
import com.milypol.security.user.UserRepository;
import com.milypol.security.warehouse.Warehouse;
import com.milypol.security.warehouse.WarehouseRepo;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class DataInitializer implements CommandLineRunner {

    private final UserRepository userRepository; // Twój repozytorium do userów
    private final BCryptPasswordEncoder passwordEncoder;
    private final WarehouseRepo warehouseRepo;

    public DataInitializer(UserRepository userRepository, WarehouseRepo warehouseRepo) {
        this.userRepository = userRepository;
        this.warehouseRepo = warehouseRepo;
        this.passwordEncoder = new BCryptPasswordEncoder();
    }

    @Override
    public void run(String... args) throws Exception {
        if(userRepository.count() == 0) { // tworzymy tylko jeśli tabela jest pusta
            Warehouse warehouse = new Warehouse();
            warehouse.setCode("A");
            warehouse.setName("Magazyn");
            warehouse.setDescription("Magazyn gowny");
            warehouseRepo.save(warehouse);

            Warehouse warehouse2 = new Warehouse();
            warehouse2.setCode("B");
            warehouse2.setName("Magazyn lewy");
            warehouse2.setDescription("Magazyn lewt");
            warehouseRepo.save(warehouse2);

            User admin = new User();

            admin.setDescription("Administrator systemu");
            admin.setEmail("test1@gmail.com");
            admin.setFirstname("Jan");
            admin.setLastName("Kowalski");
            admin.setPassword(passwordEncoder.encode("Test2025"));
            admin.setRole(Role.ROLE_ADMIN);

            User magazynier = new User();

            magazynier.setDescription("Magazynier");
            magazynier.setEmail("test2@gmail.com");
            magazynier.setFirstname("Anna");
            magazynier.setLastName("Nowak");
            magazynier.setPassword(passwordEncoder.encode("Test2025"));
            magazynier.setRole(Role.ROLE_USER);

            User kierowca = new User();

            kierowca.setDescription("Kierowca serwisowy");
            kierowca.setEmail("test3@gmail.com");
            kierowca.setFirstname("Piotr");
            kierowca.setLastName("Nowak");
            kierowca.setPassword(passwordEncoder.encode("Test2025"));
            kierowca.setRole(Role.ROLE_ADMIN);


            userRepository.save(admin);
            userRepository.save(magazynier);
            userRepository.save(kierowca);
        }
    }
}