package com.cbe.service;

import com.cbe.dto.*;
import com.cbe.entity.*;
import com.cbe.exception.ResourceNotFoundException;
import com.cbe.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AdminService {

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

    @Transactional
    public ProfileDTO ongezaMwanafunzi(RegisterRequest request) {
        if (userRepository.existsByUsername(request.getUsername())) {
            throw new RuntimeException("Username imetumika");
        }

        User user = new User(request.getUsername(), request.getPassword(), User.Role.MWANAFUNZI);
        user = userRepository.save(user);

        Wanafunzi w = new Wanafunzi();
        w.setUser(user);
        w.setNambaYaMwanafunzi(request.getNambaYaMwanafunzi());
        w.setJinaKamili(request.getJinaKamili());
        w.setJinsia(request.getJinsia());
        w.setCourse(request.getCourse());
        w.setMwakaWaKujiunga(request.getMwakaWaKujiunga());
        wanafunziRepository.save(w);

        return convertToProfileDTO(user, w);
    }

    public Kozi ongezaKozi(String code, String jina, Integer credits, String semester) {
        Kozi k = new Kozi();
        k.setCode(code);
        k.setJinaLaKozi(jina);
        k.setCredits(credits);
        k.setSemester(semester);
        return koziRepository.save(k);
    }

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

    public Malipo wekaMalipo(Long mwanafunziId, BigDecimal kiasi, String aina, String risiti, String hali) {
        Wanafunzi w = wanafunziRepository.findById(mwanafunziId)
                .orElseThrow(() -> new ResourceNotFoundException("Mwanafunzi hajapatikana"));

        Malipo m = new Malipo();
        m.setMwanafunzi(w);
        m.setKiasi(kiasi);
        m.setAinaYaMalipo(aina);
        m.setTareheYaMalipo(LocalDate.now());
        m.setRisiti(risiti);
        m.setHali(Malipo.Hali.valueOf(hali));
        return malipoRepository.save(m);
    }

    public List<ProfileDTO> wanafunziWote() {
        return wanafunziRepository.findAllWithUser().stream()
                .map(w -> convertToProfileDTO(w.getUser(), w))
                .collect(Collectors.toList());
    }

    public List<Kozi> koziZote() {
        return koziRepository.findAll();
    }

    private String computeGrade(Integer alama) {
        if (alama >= 80) return "A";
        if (alama >= 70) return "B";
        if (alama >= 60) return "C";
        if (alama >= 50) return "D";
        return "F";
    }

    private ProfileDTO convertToProfileDTO(User user, Wanafunzi w) {
        ProfileDTO dto = new ProfileDTO();
        dto.setId(user.getId());
        dto.setUsername(user.getUsername());
        dto.setRole(user.getRole().name());
        if (w != null) {
            dto.setJinaKamili(w.getJinaKamili());
            dto.setNambaYaMwanafunzi(w.getNambaYaMwanafunzi());
            dto.setJinsia(w.getJinsia());
            dto.setCourse(w.getCourse());
            dto.setMwakaWaKujiunga(w.getMwakaWaKujiunga());
        }
        return dto;
    }
}