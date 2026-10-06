package com.luminisoft.visitas.service;

import com.luminisoft.visitas.dto.AuthRequest;
import com.luminisoft.visitas.dto.AuthResponse;
import com.luminisoft.visitas.entity.User;
import com.luminisoft.visitas.repository.UserRepository;
import com.luminisoft.visitas.security.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public AuthResponse authenticate(AuthRequest request) {
        // Valida el usuario y contraseña. Si falla, lanza excepción automáticamente
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword())
        );

        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(); // Ya sabemos que existe si la autenticación pasó

        String jwtToken = jwtService.generateToken(user);
        
        return AuthResponse.builder()
                .token(jwtToken)
                .role(user.getRole())
                .build();
    }
}