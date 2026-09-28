package com.cbe.repository;

import com.cbe.entity.Taarifa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TaarifaRepository extends JpaRepository<Taarifa, Long> {

    @Query("SELECT t FROM Taarifa t LEFT JOIN FETCH t.aliyeandika ORDER BY t.tarehe DESC")
    List<Taarifa> findAllWithUser();
}