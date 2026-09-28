package com.cbe.service;

import com.cbe.dto.MalipoDTO;
import com.cbe.entity.Malipo;
import com.cbe.entity.Wanafunzi;
import com.cbe.exception.ResourceNotFoundException;
import com.cbe.repository.MalipoRepository;
import com.cbe.repository.WanafunziRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MalipoService {

    @Autowired
    private MalipoRepository malipoRepository;

    @Autowired
    private WanafunziRepository wanafunziRepository;

    public List<MalipoDTO> pataMalipoYaMwanafunzi(String username) {
        Wanafunzi mwanafunzi = wanafunziRepository.findByUserUsername(username)
                .orElseThrow(() -> new ResourceNotFoundException("Mwanafunzi hajapatikana"));

        return malipoRepository.findByMwanafunziId(mwanafunzi.getId())
                .stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public List<MalipoDTO> pataMalipoYote() {
        return malipoRepository.findAllWithDetails()
                .stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    private MalipoDTO convertToDTO(Malipo m) {
        return new MalipoDTO(
            m.getId(),
            m.getMwanafunzi().getJinaKamili(),
            m.getMwanafunzi().getNambaYaMwanafunzi(),
            m.getKiasi(),
            m.getAinaYaMalipo(),
            m.getTareheYaMalipo(),
            m.getRisiti(),
            m.getHali().name(),
            m.getMaelezo()
        );
    }
}