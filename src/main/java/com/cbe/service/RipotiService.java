package com.cbe.service;

import com.cbe.dto.TakwimuDTO;
import com.cbe.entity.Malipo;
import com.cbe.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class RipotiService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private WanafunziRepository wanafunziRepository;

    @Autowired
    private KoziRepository koziRepository;

    @Autowired
    private MatokeoRepository matokeoRepository;

    @Autowired
    private MalipoRepository malipoRepository;

    @Autowired
    private TakwimuRepository takwimuRepository;

    public TakwimuDTO pataTakwimu() {
        TakwimuDTO dto = new TakwimuDTO();

        dto.setJumlaWanafunzi(wanafunziRepository.count());
        dto.setJumlaKozi(koziRepository.count());
        dto.setJumlaMatokeo(takwimuRepository.countMatokeo());

        // Wastani wa alama
        Double avg = takwimuRepository.avgAlama();
        dto.setWastaniAlama(avg != null ? Math.round(avg * 100.0) / 100.0 : 0.0);

        // Malipo
        List<Malipo> malipo = malipoRepository.findAll();
        dto.setJumlaMalipo((long) malipo.size());

        BigDecimal jumlaKiasi = BigDecimal.ZERO;
        long waliolipa = 0;
        long hawajalipa = 0;

        for (Malipo m : malipo) {
            if (m.getKiasi() != null) {
                jumlaKiasi = jumlaKiasi.add(m.getKiasi());
            }
            if (m.getHali() == Malipo.Hali.IMELIPWA) {
                waliolipa++;
            } else if (m.getHali() == Malipo.Hali.HAIJALIPWA) {
                hawajalipa++;
            }
        }

        dto.setJumlaKiasi(jumlaKiasi);
        dto.setWanafunziWaliolipa(waliolipa);
        dto.setWanafunziHawajalipa(hawajalipa);

        return dto;
    }
}