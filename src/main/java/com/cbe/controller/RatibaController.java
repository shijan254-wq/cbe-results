package com.cbe.controller;

import com.cbe.dto.RatibaDTO;
import com.cbe.service.RatibaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/ratiba")
@CrossOrigin(origins = "*")
public class RatibaController {

    @Autowired
    private RatibaService ratibaService;

    @GetMapping("/yote")
    public ResponseEntity<List<RatibaDTO>> pataRatibaYote() {
        return ResponseEntity.ok(ratibaService.pataRatibaYote());
    }
}