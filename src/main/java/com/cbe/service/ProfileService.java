package com.cbe.service;

import com.cbe.dto.ProfileDTO;
import com.cbe.entity.User;
import com.cbe.entity.Wanafunzi;
import com.cbe.exception.ResourceNotFoundException;
import com.cbe.repository.UserRepository;
import com.cbe.repository.WanafunziRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProfileService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private WanafunziRepository wanafunziRepository;

    public ProfileDTO pataProfile(String username) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new ResourceNotFoundException("Mtumiaji hajapatikana"));

        ProfileDTO dto = new ProfileDTO();
        dto.setId(user.getId());
        dto.setUsername(user.getUsername());
        dto.setRole(user.getRole().name());

        if (user.getRole() == User.Role.MWANAFUNZI) {
            Wanafunzi w = wanafunziRepository.findByUserUsername(username).orElse(null);
            if (w != null) {
                dto.setJinaKamili(w.getJinaKamili());
                dto.setNambaYaMwanafunzi(w.getNambaYaMwanafunzi());
                dto.setJinsia(w.getJinsia());
                dto.setCourse(w.getCourse());
                dto.setMwakaWaKujiunga(w.getMwakaWaKujiunga());
            }
        } else {
            dto.setJinaKamili(user.getUsername());
        }

        return dto;
    }
}