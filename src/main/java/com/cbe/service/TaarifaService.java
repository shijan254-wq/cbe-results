package com.cbe.service;

import com.cbe.dto.TaarifaDTO;
import com.cbe.entity.Taarifa;
import com.cbe.entity.User;
import com.cbe.repository.TaarifaRepository;
import com.cbe.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TaarifaService {

    @Autowired
    private TaarifaRepository taarifaRepository;

    @Autowired
    private UserRepository userRepository;

    public List<TaarifaDTO> pataTaarifaZote() {
        return taarifaRepository.findAllWithUser().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public TaarifaDTO ongezaTaarifa(String kichwa, String maudhui, String aina,
                                     String walengwa, String username) {
        User user = userRepository.findByUsername(username).orElse(null);

        Taarifa t = new Taarifa();
        t.setKichwa(kichwa);
        t.setMaudhui(maudhui);
        t.setAina(Taarifa.Aina.valueOf(aina));
        t.setWalengwa(Taarifa.Walengwa.valueOf(walengwa));
        t.setAliyeandika(user);

        taarifaRepository.save(t);
        return convertToDTO(t);
    }

    private TaarifaDTO convertToDTO(Taarifa t) {
        return new TaarifaDTO(
            t.getId(),
            t.getKichwa(),
            t.getMaudhui(),
            t.getAina().name(),
            t.getWalengwa().name(),
            t.getAliyeandika() != null ? t.getAliyeandika().getUsername() : "-",
            t.getTarehe()
        );
    }
}