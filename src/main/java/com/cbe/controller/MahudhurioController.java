package com.cbe.controller;

import com.cbe.dto.MahudhurioDTO;
import com.cbe.service.MahudhurioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/mahudhurio")
@CrossOrigin(origins = "*")
public class MahudhurioController {

    @Autowired
    private MahudhurioService mahudhurioService;

    @GetMapping("/yangu")
    public ResponseEntity<List<MahudhurioDTO>> pataMahudhurioYangu(Authentication auth) {
        return ResponseEntity.ok(mahudhurioService.pataMahudhurioYaMwanafunzi(auth.getName()));
    }

    @GetMapping("/yote")
    public ResponseEntity<List<MahudhurioDTO>> pataMahudhurioYote() {
        return ResponseEntity.ok(mahudhurioService.pataMahudhurioYote());
    }
}