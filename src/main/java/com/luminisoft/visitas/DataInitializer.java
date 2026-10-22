package com.luminisoft.visitas;

import com.luminisoft.visitas.entity.User;
import com.luminisoft.visitas.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DataInitializer implements CommandLineRunner {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) throws Exception {
        if (userRepository.findByEmail("admin@test.com").isEmpty()) {
            User testUser = new User();
            testUser.setName("Admin de Prueba");
            testUser.setEmail("admin@test.com");
            // Encriptamos la contraseña "123456"
            testUser.setPassword(passwordEncoder.encode("123456")); 
            testUser.setRole("ADMINISTRADOR"); 
            
            userRepository.save(testUser);
            System.out.println("✅ Usuario de prueba creado exitosamente: admin@test.com / 123456");
        }
    }
}