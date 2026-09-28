package com.cbe.controller;

import com.cbe.entity.Mahudhurio;
import com.cbe.entity.Matokeo;
import com.cbe.service.MwalimuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/mwalimu")
@CrossOrigin(origins = "*")
@PreAuthorize("hasAnyRole('MWALIMU', 'ADMIN')")
public class MwalimuController {

    @Autowired
    private MwalimuService mwalimuService;

    @PostMapping("/matokeo")
    public ResponseEntity<Matokeo> wekaMatokeo(
            @RequestParam Long mwanafunziId,
            @RequestParam Long koziId,
            @RequestParam Integer alama,
            @RequestParam String semester,
            @RequestParam Integer mwaka) {
        return ResponseEntity.ok(mwalimuService.wekaMatokeo(mwanafunziId, koziId, alama, semester, mwaka));
    }

    @PostMapping("/mahudhurio")
    public ResponseEntity<Mahudhurio> wekaMahudhurio(
            @RequestParam Long mwanafunziId,
            @RequestParam Long koziId,
            @RequestParam String hali,
            @RequestParam String tarehe) {
        return ResponseEntity.ok(mwalimuService.wekaMahudhurio(mwanafunziId, koziId, hali, tarehe));
    }
}