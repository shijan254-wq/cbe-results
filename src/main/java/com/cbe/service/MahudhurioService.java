package com.cbe.service;

import com.cbe.dto.MahudhurioDTO;
import com.cbe.entity.Mahudhurio;
import com.cbe.entity.Wanafunzi;
import com.cbe.exception.ResourceNotFoundException;
import com.cbe.repository.MahudhurioRepository;
import com.cbe.repository.WanafunziRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MahudhurioService {

    @Autowired
    private MahudhurioRepository mahudhurioRepository;

    @Autowired
    private WanafunziRepository wanafunziRepository;

    public List<MahudhurioDTO> pataMahudhurioYaMwanafunzi(String username) {
        Wanafunzi mwanafunzi = wanafunziRepository.findByUserUsername(username)
                .orElseThrow(() -> new ResourceNotFoundException("Mwanafunzi hajapatikana"));

        return mahudhurioRepository.findByMwanafunziId(mwanafunzi.getId()).stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public List<MahudhurioDTO> pataMahudhurioYote() {
        return mahudhurioRepository.findAllWithDetails().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    private MahudhurioDTO convertToDTO(Mahudhurio m) {
        return new MahudhurioDTO(
            m.getId(),
            m.getMwanafunzi().getJinaKamili(),
            m.getMwanafunzi().getNambaYaMwanafunzi(),
            m.getKozi().getCode(),
            m.getKozi().getJinaLaKozi(),
            m.getTarehe(),
            m.getHali().name(),
            m.getMaelezo()
        );
    }
}