package com.cbe.controller;

import com.cbe.dto.ProfileDTO;
import com.cbe.service.ProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/profile")
@CrossOrigin(origins = "*")
public class ProfileController {

    @Autowired
    private ProfileService profileService;

    @GetMapping("/yangu")
    public ResponseEntity<ProfileDTO> pataProfile(Authentication auth) {
        return ResponseEntity.ok(profileService.pataProfile(auth.getName()));
    }
}