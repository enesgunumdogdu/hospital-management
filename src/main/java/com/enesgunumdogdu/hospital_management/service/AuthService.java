package com.enesgunumdogdu.hospital_management.service;

import com.enesgunumdogdu.hospital_management.domain.dto.AuthRequest;
import com.enesgunumdogdu.hospital_management.domain.dto.AuthResponse;
import com.enesgunumdogdu.hospital_management.domain.dto.RegisterRequest;

public interface AuthService {
    AuthResponse register(RegisterRequest request);
    AuthResponse login(AuthRequest request);
}
