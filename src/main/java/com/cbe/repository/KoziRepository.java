package com.cbe.repository;

import com.cbe.entity.Kozi;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface KoziRepository extends JpaRepository<Kozi, Long> {
    Optional<Kozi> findByCode(String code);
}