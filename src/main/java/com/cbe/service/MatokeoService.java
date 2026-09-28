package com.cbe.service;

import com.cbe.dto.MatokeoDTO;
import com.cbe.entity.Matokeo;
import com.cbe.entity.Wanafunzi;
import com.cbe.exception.ResourceNotFoundException;
import com.cbe.repository.MatokeoRepository;
import com.cbe.repository.WanafunziRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MatokeoService {

    @Autowired
    private MatokeoRepository matokeoRepository;

    @Autowired
    private WanafunziRepository wanafunziRepository;

    @Transactional(readOnly = true)
    public List<MatokeoDTO> pataMatokeoYaMwanafunzi(String username) {
        Wanafunzi mwanafunzi = wanafunziRepository.findByUserUsername(username)
                .orElseThrow(() -> new ResourceNotFoundException("Mwanafunzi hajapatikana"));

        return matokeoRepository.findByMwanafunziId(mwanafunzi.getId())
                .stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public List<MatokeoDTO> pataMatokeoYote() {
        return matokeoRepository.findAllWithDetails()
                .stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public List<MatokeoDTO> tafuta(String namba) {
        Wanafunzi mwanafunzi = wanafunziRepository.findByNambaYaMwanafunzi(namba).orElse(null);

        if (mwanafunzi == null) {
            mwanafunzi = wanafunziRepository.findByUserUsername(namba).orElse(null);
        }

        if (mwanafunzi == null) {
            throw new ResourceNotFoundException("Mwanafunzi hajapatikana");
        }

        return matokeoRepository.findByMwanafunziId(mwanafunzi.getId())
                .stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    private MatokeoDTO convertToDTO(Matokeo m) {
        MatokeoDTO dto = new MatokeoDTO(
            m.getId(),
            m.getKozi().getCode(),
            m.getKozi().getJinaLaKozi(),
            m.getKozi().getCredits(),
            m.getAlama(),
            m.getGrade(),
            m.getMwaka(),
            m.getSemester(),
            m.getTarehe()
        );
        dto.setJinaKamili(m.getMwanafunzi().getJinaKamili());
        return dto;
    }
}