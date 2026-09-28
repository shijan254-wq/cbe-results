package com.cbe.controller;

import com.cbe.dto.TaarifaDTO;
import com.cbe.service.TaarifaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/taarifa")
@CrossOrigin(origins = "*")
public class TaarifaController {

    @Autowired
    private TaarifaService taarifaService;

    @GetMapping("/zote")
    public ResponseEntity<List<TaarifaDTO>> pataTaarifaZote() {
        return ResponseEntity.ok(taarifaService.pataTaarifaZote());
    }

    @PostMapping("/ongeza")
    public ResponseEntity<TaarifaDTO> ongezaTaarifa(
            @RequestParam String kichwa,
            @RequestParam String maudhui,
            @RequestParam String aina,
            @RequestParam String walengwa,
            Authentication auth) {
        return ResponseEntity.ok(taarifaService.ongezaTaarifa(kichwa, maudhui, aina, walengwa, auth.getName()));
    }
}