package com.cbe.service;

import com.cbe.entity.*;
import com.cbe.exception.ResourceNotFoundException;
import com.cbe.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Service
public class MwalimuService {

    @Autowired
    private WanafunziRepository wanafunziRepository;

    @Autowired
    private KoziRepository koziRepository;

    @Autowired
    private MatokeoRepository matokeoRepository;

    @Autowired
    private MahudhurioRepository mahudhurioRepository;

    // Weka matokeo
    @Transactional
    public Matokeo wekaMatokeo(Long mwanafunziId, Long koziId, Integer alama, String semester, Integer mwaka) {
        Wanafunzi w = wanafunziRepository.findById(mwanafunziId)
                .orElseThrow(() -> new ResourceNotFoundException("Mwanafunzi hajapatikana"));
        Kozi k = koziRepository.findById(koziId)
                .orElseThrow(() -> new ResourceNotFoundException("Kozi haijapatikana"));

        Matokeo m = new Matokeo();
        m.setMwanafunzi(w);
        m.setKozi(k);
        m.setAlama(alama);
        m.setGrade(computeGrade(alama));
        m.setSemester(semester);
        m.setMwaka(mwaka);
        return matokeoRepository.save(m);
    }

    // Weka mahudhurio
    @Transactional
    public Mahudhurio wekaMahudhurio(Long mwanafunziId, Long koziId, String hali, String tarehe) {
        Wanafunzi w = wanafunziRepository.findById(mwanafunziId)
                .orElseThrow(() -> new ResourceNotFoundException("Mwanafunzi hajapatikana"));
        Kozi k = koziRepository.findById(koziId)
                .orElseThrow(() -> new ResourceNotFoundException("Kozi haijapatikana"));

        Mahudhurio m = new Mahudhurio();
        m.setMwanafunzi(w);
        m.setKozi(k);
        m.setTarehe(LocalDate.parse(tarehe));
        m.setHali(Mahudhurio.Hali.valueOf(hali));
        return mahudhurioRepository.save(m);
    }

    // Compute grade
    private String computeGrade(Integer alama) {
        if (alama >= 80) return "A";
        if (alama >= 70) return "B";
        if (alama >= 60) return "C";
        if (alama >= 50) return "D";
        return "F";
    }
}