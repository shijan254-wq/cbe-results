package com.cbe.controller;

import com.cbe.dto.ProfileDTO;
import com.cbe.dto.RegisterRequest;
import com.cbe.entity.Kozi;
import com.cbe.entity.Malipo;
import com.cbe.entity.Matokeo;
import com.cbe.service.AdminService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/admin")
@CrossOrigin(origins = "*")
public class AdminController {

    @Autowired
    private AdminService adminService;

    // POST - Ongeza mwanafunzi
    @PostMapping("/mwanafunzi")
    public ResponseEntity<?> ongezaMwanafunzi(@Valid @RequestBody RegisterRequest request) {
        try {
            ProfileDTO profile = adminService.ongezaMwanafunzi(request);
            return ResponseEntity.ok(profile);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(Map.of("message", e.getMessage()));
        }
    }

    // GET - Wanafunzi wote
    @GetMapping("/wanafunzi")
    public ResponseEntity<List<ProfileDTO>> wanafunziWote() {
        return ResponseEntity.ok(adminService.wanafunziWote());
    }

    @PostMapping("/kozi")
    public ResponseEntity<Kozi> ongezaKozi(@RequestBody Kozi kozi) {
        return ResponseEntity.ok(adminService.ongezaKozi(
                kozi.getCode(), kozi.getJinaLaKozi(), kozi.getCredits(), kozi.getSemester()));
    }

    @GetMapping("/kozi")
    public ResponseEntity<List<Kozi>> koziZote() {
        return ResponseEntity.ok(adminService.koziZote());
    }

    @PostMapping("/matokeo")
    public ResponseEntity<Matokeo> wekaMatokeo(
            @RequestParam Long mwanafunziId,
            @RequestParam Long koziId,
            @RequestParam Integer alama,
            @RequestParam String semester,
            @RequestParam Integer mwaka) {
        return ResponseEntity.ok(adminService.wekaMatokeo(mwanafunziId, koziId, alama, semester, mwaka));
    }

    @PostMapping("/malipo")
    public ResponseEntity<Malipo> wekaMalipo(
            @RequestParam Long mwanafunziId,
            @RequestParam BigDecimal kiasi,
            @RequestParam String aina,
            @RequestParam String risiti,
            @RequestParam String hali) {
        return ResponseEntity.ok(adminService.wekaMalipo(mwanafunziId, kiasi, aina, risiti, hali));
    }
}