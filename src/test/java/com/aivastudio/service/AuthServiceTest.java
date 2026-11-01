package com.aivastudio.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.aivastudio.exception.CredencialesInvalidasException;
import com.aivastudio.model.dto.AuthRequest;
import com.aivastudio.model.dto.RegistrationRequest;
import com.aivastudio.model.entity.CreditMovement;
import com.aivastudio.model.entity.User;
import com.aivastudio.repository.CreditRepository;
import com.aivastudio.repository.UserRepository;

@ExtendWith(MockitoExtension.class)
class AuthServiceTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private CreditRepository creditMovementRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @Mock
    private TokenService tokenService;

    @InjectMocks
    private AuthService authService;

    @Test
    void registerNormalizesEmailAndCreatesInitialCredits() {
        when(userRepository.save(any(User.class))).thenAnswer(invocation -> invocation.getArgument(0));
        when(passwordEncoder.encode("secret123")).thenReturn("encoded-password");
        when(tokenService.issue(any(User.class))).thenReturn("issued-token");

        var response = authService.register(
                new RegistrationRequest(" Ana ", " ANA@Example.com ", "secret123"));

        assertEquals("issued-token", response.token());
        assertEquals("Ana", response.usuario().nombre());
        assertEquals("ana@example.com", response.usuario().email());
        assertEquals(100, response.usuario().creditosDisponibles());

        ArgumentCaptor<User> userCaptor = ArgumentCaptor.forClass(User.class);
        verify(userRepository).save(userCaptor.capture());
        assertEquals("encoded-password", userCaptor.getValue().getPasswordHash());

        ArgumentCaptor<CreditMovement> movementCaptor = ArgumentCaptor.forClass(CreditMovement.class);
        verify(creditMovementRepository).save(movementCaptor.capture());
        assertEquals(100, movementCaptor.getValue().getAmount());
        assertEquals("Créditos iniciales", movementCaptor.getValue().getDescription());
    }

    @Test
    void loginNormalizesEmailAndReturnsSession() {
        User user = new User("Ana", "ana@example.com", "encoded-password", 100);
        when(userRepository.findByEmailIgnoreCase("ana@example.com")).thenReturn(Optional.of(user));
        when(passwordEncoder.matches("secret123", "encoded-password")).thenReturn(true);
        when(tokenService.issue(user)).thenReturn("issued-token");

        var response = authService.login(new AuthRequest(" ANA@Example.com ", "secret123"));

        assertEquals("issued-token", response.token());
        assertEquals("ana@example.com", response.usuario().email());
    }

    @Test
    void loginRejectsIncorrectPassword() {
        User user = new User("Ana", "ana@example.com", "encoded-password", 100);
        when(userRepository.findByEmailIgnoreCase("ana@example.com")).thenReturn(Optional.of(user));
        when(passwordEncoder.matches("incorrecta", "encoded-password")).thenReturn(false);

        assertThrows(
                CredencialesInvalidasException.class,
                () -> authService.login(new AuthRequest("ana@example.com", "incorrecta")));
    }
}
