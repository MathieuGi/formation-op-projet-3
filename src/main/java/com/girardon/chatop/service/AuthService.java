package com.girardon.chatop.service;

import com.girardon.chatop.dto.AuthResponse;
import com.girardon.chatop.dto.LoginRequest;
import com.girardon.chatop.dto.RegisterRequest;
import com.girardon.chatop.config.JwtService;
import com.girardon.chatop.exception.UserExistsException;
import com.girardon.chatop.model.User;
import com.girardon.chatop.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final JwtService jwtService;
    private final UserRepository userRepository;
    private final AuthenticationManager authenticationManager;
    private final PasswordEncoder passwordEncoder;


    @Transactional
    public AuthResponse register(User user) {
        if (userRepository.existsByEmail(user.getEmail())) {
            throw new UserExistsException("Email already exists");
        }

        String hashedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(hashedPassword);

        userRepository.save(user);

        String jwt = jwtService.generateToken(user);

        return new AuthResponse(jwt);
    }

    public AuthResponse login(String email, String password) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                email,
                password
        ));

        User user = userRepository.findByEmail(email)
                .orElseThrow(
                        () -> new UsernameNotFoundException("This user doesn't exists")
                );

        String jwt = jwtService.generateToken(user);

        return new AuthResponse(jwt);
    }
}
