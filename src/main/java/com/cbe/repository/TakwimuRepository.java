package com.cbe.repository;

import com.cbe.entity.Matokeo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface TakwimuRepository extends JpaRepository<Matokeo, Long> {

    @Query("SELECT COUNT(m) FROM Matokeo m")
    Long countMatokeo();

    @Query("SELECT AVG(m.alama) FROM Matokeo m")
    Double avgAlama();
}