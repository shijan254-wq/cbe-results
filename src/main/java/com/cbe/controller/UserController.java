package com.cbe.controller;

import com.cbe.dto.ChangePasswordRequest;
import com.cbe.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/user")
@CrossOrigin(origins = "*")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/badilisha-password")
    public ResponseEntity<?> badilishaPassword(@Valid @RequestBody ChangePasswordRequest request,
                                                Authentication auth) {
        userService.badilishaPassword(auth.getName(), request);
        return ResponseEntity.ok(Map.of("message", "Password imebadilishwa!"));
    }

    @PostMapping("/zimwa/{id}")
    public ResponseEntity<?> zimwaMtumiaji(@PathVariable Long id) {
        userService.zimwaMtumiaji(id);
        return ResponseEntity.ok(Map.of("message", "Mtumiaji amezimwa"));
    }

    @PostMapping("/washa/{id}")
    public ResponseEntity<?> washaMtumiaji(@PathVariable Long id) {
        userService.washaMtumiaji(id);
        return ResponseEntity.ok(Map.of("message", "Mtumiaji amewashwa"));
    }
}