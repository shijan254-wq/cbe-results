package com.cbe.repository;

import com.cbe.entity.Malipo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MalipoRepository extends JpaRepository<Malipo, Long> {

    @Query("SELECT m FROM Malipo m " +
           "JOIN FETCH m.mwanafunzi w " +
           "WHERE w.id = :mwanafunziId " +
           "ORDER BY m.tareheYaMalipo DESC")
    List<Malipo> findByMwanafunziId(@Param("mwanafunziId") Long mwanafunziId);

    @Query("SELECT m FROM Malipo m " +
           "JOIN FETCH m.mwanafunzi w " +
           "ORDER BY w.jinaKamili, m.tareheYaMalipo DESC")
    List<Malipo> findAllWithDetails();
}