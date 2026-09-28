package com.cbe.repository;

import com.cbe.entity.Mahudhurio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MahudhurioRepository extends JpaRepository<Mahudhurio, Long> {

    @Query("SELECT m FROM Mahudhurio m JOIN FETCH m.mwanafunzi w JOIN FETCH m.kozi k " +
           "WHERE w.id = :mwanafunziId ORDER BY m.tarehe DESC")
    List<Mahudhurio> findByMwanafunziId(@Param("mwanafunziId") Long mwanafunziId);

    @Query("SELECT m FROM Mahudhurio m JOIN FETCH m.mwanafunzi w JOIN FETCH m.kozi k " +
           "ORDER BY m.tarehe DESC")
    List<Mahudhurio> findAllWithDetails();
}