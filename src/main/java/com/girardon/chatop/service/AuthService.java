package com.girardon.chatop.service;

import com.girardon.chatop.DTO.AuthResponse;
import com.girardon.chatop.DTO.LoginRequest;
import com.girardon.chatop.DTO.RegisterRequest;
import com.girardon.chatop.exception.UserExistsException;
import com.girardon.chatop.model.User;
import com.girardon.chatop.repository.UserRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final JwtService jwtService;
    private final UserRepository userRepository;
    private final AuthenticationManager authenticationManager;
    private final PasswordEncoder passwordEncoder;


    @Transactional
    public AuthResponse register(RegisterRequest request) {

        if (userRepository.existsByEmail(request.email())) {
            throw new UserExistsException("Email already exists");
        }

        String hashedPassword = passwordEncoder.encode(request.password());

        User user = new User();
        user.setName(request.name());
        user.setEmail(request.email());
        user.setPassword(hashedPassword);

        userRepository.save(user);

        String jwt = jwtService.generateToken(user);

        return new AuthResponse(jwt);
    }
    
    public AuthResponse login(LoginRequest request) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                request.email(),
                request.password()
        ));

        User user = userRepository.findByEmail(request.email())
                .orElseThrow(
                        () -> new UsernameNotFoundException("This user doesn't exists")
                );

        String jwt = jwtService.generateToken(user);

        return new AuthResponse(jwt);
    }
}
