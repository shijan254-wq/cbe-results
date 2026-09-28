package com.cbe.controller;

import com.cbe.dto.StatisticsDTO;
import com.cbe.entity.Malipo;
import com.cbe.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/statistics")
@CrossOrigin(origins = "*")
@PreAuthorize("hasAnyRole('MWALIMU', 'ADMIN')")
public class StatisticsController {

    @Autowired
    private WanafunziRepository wanafunziRepository;

    @Autowired
    private KoziRepository koziRepository;

    @Autowired
    private MatokeoRepository matokeoRepository;

    @Autowired
    private MalipoRepository malipoRepository;

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/takwimu")
    public ResponseEntity<StatisticsDTO> pataTakwimu() {
        StatisticsDTO dto = new StatisticsDTO();
        dto.setJumlaWanafunzi(wanafunziRepository.count());
        dto.setJumlaKozi(koziRepository.count());
        dto.setJumlaMatokeo(matokeoRepository.count());
        dto.setJumlaMalipo(malipoRepository.count());
        dto.setJumlaWalimu(userRepository.count());

        Double avg = matokeoRepository.findAll().stream()
                .mapToInt(m -> m.getAlama() != null ? m.getAlama() : 0)
                .average().orElse(0.0);
        dto.setWastaniAlama(Math.round(avg * 100.0) / 100.0);

        List<Malipo> malipo = malipoRepository.findAll();
        long waliolipa = malipo.stream().filter(m -> m.getHali() == Malipo.Hali.IMELIPWA).count();
        long hawajalipa = malipo.stream().filter(m -> m.getHali() == Malipo.Hali.HAIJALIPWA).count();
        dto.setWanafunziWaliolipa(waliolipa);
        dto.setWanafunziHawajalipa(hawajalipa);

        return ResponseEntity.ok(dto);
    }
}