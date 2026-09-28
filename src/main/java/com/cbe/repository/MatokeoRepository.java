package com.cbe.repository;

import com.cbe.entity.Matokeo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MatokeoRepository extends JpaRepository<Matokeo, Long> {

    @Query("SELECT m FROM Matokeo m " +
           "JOIN FETCH m.kozi k " +
           "JOIN FETCH m.mwanafunzi w " +
           "WHERE w.id = :mwanafunziId " +
           "ORDER BY m.mwaka DESC, m.semester DESC")
    List<Matokeo> findByMwanafunziId(@Param("mwanafunziId") Long mwanafunziId);

    @Query("SELECT m FROM Matokeo m " +
           "JOIN FETCH m.kozi k " +
           "JOIN FETCH m.mwanafunzi w " +
           "ORDER BY w.jinaKamili, m.mwaka DESC")
    List<Matokeo> findAllWithDetails();
}