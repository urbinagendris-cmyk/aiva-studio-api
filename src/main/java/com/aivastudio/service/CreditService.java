package com.aivastudio.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.aivastudio.exception.RecursoNoEncontradoException;
import com.aivastudio.exception.ReglaNegocioException;
import com.aivastudio.model.dto.CreditMovementResponse;
import com.aivastudio.model.dto.RechargeRequest;
import com.aivastudio.model.dto.RechargeResponse;
import com.aivastudio.model.dto.UserResponse;
import com.aivastudio.model.entity.CreditMovement;
import com.aivastudio.model.entity.User;
import com.aivastudio.repository.CreditMovementRepository;
import com.aivastudio.repository.UserRepository;

@Service
public class CreditService {

    private static final long MINIMUM_RECHARGE = 50;
    private static final long BONUS_THRESHOLD = 500;
    private static final long BONUS_PERCENT = 20;

    private final CurrentUserService currentUserService;
    private final UserRepository userRepository;
    private final CreditMovementRepository creditMovementRepository;

    public CreditService(
            CurrentUserService currentUserService,
            UserRepository userRepository,
            CreditMovementRepository creditMovementRepository) {
        this.currentUserService = currentUserService;
        this.userRepository = userRepository;
        this.creditMovementRepository = creditMovementRepository;
    }

    @Transactional(readOnly = true)
    public UserResponse getProfile(long userId) {
        return UserResponse.from(currentUserService.requireUser(userId));
    }

    @Transactional(readOnly = true)
    public List<CreditMovementResponse> listMovements(long userId) {
        currentUserService.requireUser(userId);
        return creditMovementRepository.findAllByUser_IdOrderByCreatedAtDesc(userId)
                .stream()
                .map(CreditMovementResponse::from)
                .toList();
    }

    @Transactional
    public RechargeResponse recharge(RechargeRequest request) {
        User currentUser = currentUserService.currentUser();
        if (request.usuarioId() == null || !currentUser.getId().equals(request.usuarioId())) {
            throw new ReglaNegocioException("No puedes operar sobre otra cuenta");
        }
        User user = userRepository.findLockedById(request.usuarioId())
                .orElseThrow(() -> new RecursoNoEncontradoException("Usuario no encontrado"));

        Long amount = request.cantidad();
        if (amount == null || amount < MINIMUM_RECHARGE) {
            throw new ReglaNegocioException("La recarga mínima es de 50 créditos");
        }

        long bonus;
        long total;
        long updatedCredits;
        try {
            bonus = amount >= BONUS_THRESHOLD
                    ? Math.floorDiv(Math.multiplyExact(amount, BONUS_PERCENT), 100)
                    : 0;
            total = Math.addExact(amount, bonus);
            updatedCredits = Math.addExact(user.getCredits(), total);
        } catch (ArithmeticException ex) {
            throw new ReglaNegocioException("La recarga supera el saldo máximo permitido");
        }
        user.setCredits(updatedCredits);
        creditMovementRepository.save(new CreditMovement(
                user,
                total,
                bonus > 0 ? "Recarga de prueba (incluye bono)" : "Recarga de prueba"));

        return new RechargeResponse(user.getId(), amount, bonus, total, updatedCredits);
    }
}
