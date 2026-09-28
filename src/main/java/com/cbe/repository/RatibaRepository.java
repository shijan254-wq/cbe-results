package com.cbe.repository;

import com.cbe.entity.Ratiba;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RatibaRepository extends JpaRepository<Ratiba, Long> {

    @Query("SELECT r FROM Ratiba r JOIN FETCH r.kozi ORDER BY r.siku, r.mudaWaKwanza")
    List<Ratiba> findAllWithKozi();
}