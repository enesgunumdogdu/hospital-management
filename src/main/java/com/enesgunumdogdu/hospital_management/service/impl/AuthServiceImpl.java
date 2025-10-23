package com.enesgunumdogdu.hospital_management.service.impl;

import com.enesgunumdogdu.hospital_management.domain.dto.AuthRequest;
import com.enesgunumdogdu.hospital_management.domain.dto.AuthResponse;
import com.enesgunumdogdu.hospital_management.domain.dto.RegisterRequest;
import com.enesgunumdogdu.hospital_management.domain.entities.User;
import com.enesgunumdogdu.hospital_management.repository.UserRepository;
import com.enesgunumdogdu.hospital_management.security.JwtUtil;
import com.enesgunumdogdu.hospital_management.service.AuthService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;
    private final AuthenticationManager authenticationManager;

    public AuthServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder, 
                          JwtUtil jwtUtil, AuthenticationManager authenticationManager) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtUtil = jwtUtil;
        this.authenticationManager = authenticationManager;
    }

    @Override
    public AuthResponse register(RegisterRequest request) {
        if (userRepository.existsByUsername(request.getUsername())) {
            throw new RuntimeException("Username already exists");
        }
        
        if (userRepository.existsByEmail(request.getEmail())) {
            throw new RuntimeException("Email already exists");
        }

        User user = new User();
        user.setUsername(request.getUsername());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setEmail(request.getEmail());
        
        User savedUser = userRepository.save(user);
        
        String token = jwtUtil.generateToken(savedUser);
        
        return new AuthResponse(token, savedUser.getUsername(), savedUser.getEmail());
    }

    @Override
    public AuthResponse login(AuthRequest request) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getUsername(),
                        request.getPassword()
                )
        );

        User user = (User) authentication.getPrincipal();
        
        String token = jwtUtil.generateToken(user);
        
        return new AuthResponse(token, user.getUsername(), user.getEmail());
    }
}
