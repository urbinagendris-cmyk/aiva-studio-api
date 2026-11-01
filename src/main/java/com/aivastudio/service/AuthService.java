package com.aivastudio.service;

import java.util.Locale;
import java.util.regex.Pattern;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.aivastudio.exception.CredencialesInvalidasException;
import com.aivastudio.exception.ReglaNegocioException;
import com.aivastudio.model.dto.AuthRequest;
import com.aivastudio.model.dto.AuthResponse;
import com.aivastudio.model.dto.RegistrationRequest;
import com.aivastudio.model.dto.UserResponse;
import com.aivastudio.model.entity.CreditMovement;
import com.aivastudio.model.entity.User;
import com.aivastudio.repository.CreditMovementRepository;
import com.aivastudio.repository.UserRepository;

public class AuthService {
    
    private static final long INITIAL_CREDITS = 100;
    private static final Pattern EMAIL_PATTERN = Pattern.compile("^[^\\s@]+@[^\\s@]+\\.[^\\s@]+$");
    
    private final UserRepository userRepository;
    private final CreditMovementRepository creditMovementRepository;
    private final PasswordEncoder passwordEncoder;
    private final TokenService tokenService;
    
    public AuthService(
        UserRepository userRepository,
        CreditMovementRepository creditMovementRepository,
        PasswordEncoder passwordEncoder,
        TokenService tokenService) {
            this.userRepository = userRepository;
            this.creditMovementRepository = creditMovementRepository;
            this.passwordEncoder = passwordEncoder;
            this.tokenService = tokenService;
        }
        
        @Transactional
        public AuthResponse register(RegistrationRequest request) {
            String name = request.nombre() == null ? "" : request.nombre().trim();
            @Service
        String email = normalizeEmail(request.email());
        String password = request.password();

        if (name.isBlank() || name.length() > 80) {
            throw new ReglaNegocioException("El nombre es obligatorio y no puede superar 80 caracteres");
        }
        validateEmail(email);
        validatePassword(password);
        if (userRepository.existsByEmailIgnoreCase(email)) {
            throw new ReglaNegocioException("Ya existe una cuenta con ese correo electrónico");
        }

        User user = userRepository.save(new User(
                name,
                email,
                passwordEncoder.encode(password),
                INITIAL_CREDITS));
        creditMovementRepository.save(new CreditMovement(user, INITIAL_CREDITS, "Créditos iniciales"));
        return createAuthResponse(user);
    }

    @Transactional
    public AuthResponse login(AuthRequest request) {
        String email = normalizeEmail(request.email());
        String password = request.password();
        if (email.isBlank() || password == null) {
            throw new CredencialesInvalidasException();
        }

        User user = userRepository.findByEmailIgnoreCase(email)
                .orElseThrow(CredencialesInvalidasException::new);
        if (!passwordEncoder.matches(password, user.getPasswordHash())) {
            throw new CredencialesInvalidasException();
        }
        return createAuthResponse(user);
    }

    @Transactional
    public void logout(String token) {
        if (token != null && !token.isBlank()) {
            tokenService.revoke(token);
        }
    }

    private AuthResponse createAuthResponse(User user) {
        return new AuthResponse(tokenService.issue(user), UserResponse.from(user));
    }

    private String normalizeEmail(String email) {
        return email == null ? "" : email.trim().toLowerCase(Locale.ROOT);
    }

    private void validateEmail(String email) {
        if (email.length() > 254 || !EMAIL_PATTERN.matcher(email).matches()) {
            throw new ReglaNegocioException("El correo electrónico no es válido");
        }
    }

    private void validatePassword(String password) {
        if (password == null
                || password.length() < 6
                || password.getBytes(java.nio.charset.StandardCharsets.UTF_8).length > 72) {
            throw new ReglaNegocioException("La contraseña debe tener entre 6 y 72 caracteres");
        }
    }
    
}