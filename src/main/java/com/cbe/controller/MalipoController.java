package com.cbe.controller;

import com.cbe.dto.MalipoDTO;
import com.cbe.service.MalipoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/malipo")
@CrossOrigin(origins = "*")
public class MalipoController {

    @Autowired
    private MalipoService malipoService;

    @GetMapping("/yangu")
    public ResponseEntity<List<MalipoDTO>> pataMalipoYangu(Authentication auth) {
        return ResponseEntity.ok(malipoService.pataMalipoYaMwanafunzi(auth.getName()));
    }

    @GetMapping("/yote")
    public ResponseEntity<List<MalipoDTO>> pataMalipoYote() {
        return ResponseEntity.ok(malipoService.pataMalipoYote());
    }
}