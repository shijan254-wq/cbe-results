package com.cbe.controller;

import com.cbe.dto.TakwimuDTO;
import com.cbe.service.RipotiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/ripoti")
@CrossOrigin(origins = "*")
@PreAuthorize("hasAnyRole('MWALIMU', 'ADMIN', 'AFISA_MALIPO')")
public class RipotiController {

    @Autowired
    private RipotiService ripotiService;

    @GetMapping("/takwimu")
    public ResponseEntity<TakwimuDTO> pataTakwimu() {
        return ResponseEntity.ok(ripotiService.pataTakwimu());
    }
}