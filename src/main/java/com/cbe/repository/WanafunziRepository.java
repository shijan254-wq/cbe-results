package com.cbe.repository;

import com.cbe.entity.Wanafunzi;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface WanafunziRepository extends JpaRepository<Wanafunzi, Long> {

    @Query("SELECT w FROM Wanafunzi w JOIN FETCH w.user")
    List<Wanafunzi> findAllWithUser();

    Optional<Wanafunzi> findByUserUsername(String username);
    Optional<Wanafunzi> findByNambaYaMwanafunzi(String namba);
}