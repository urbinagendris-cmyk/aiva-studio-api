package com.aivastudio.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.aivastudio.model.entity.CreditMovement;

public interface CreditRepository extends JpaRepository<CreditMovement, Long> {

    List<CreditMovement> findAllByUser_IdOrderByCreatedAtDesc(Long userId);
}