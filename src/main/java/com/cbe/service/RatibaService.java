package com.cbe.service;

import com.cbe.dto.RatibaDTO;
import com.cbe.entity.Ratiba;
import com.cbe.repository.RatibaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RatibaService {

    @Autowired
    private RatibaRepository ratibaRepository;

    public List<RatibaDTO> pataRatibaYote() {
        return ratibaRepository.findAllWithKozi().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    private RatibaDTO convertToDTO(Ratiba r) {
        return new RatibaDTO(
            r.getId(),
            r.getKozi().getCode(),
            r.getKozi().getJinaLaKozi(),
            r.getSiku().name(),
            r.getMudaWaKwanza().toString(),
            r.getMudaWaMwisho().toString(),
            r.getDarasa(),
            r.getMwalimu()
        );
    }
}