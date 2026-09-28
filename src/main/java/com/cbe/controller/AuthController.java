package com.cbe.controller;

import com.cbe.dto.LoginRequest;
import com.cbe.dto.LoginResponse;
import com.cbe.dto.RegisterRequest;
import com.cbe.entity.User;
import com.cbe.entity.Wanafunzi;
import com.cbe.repository.UserRepository;
import com.cbe.repository.WanafunziRepository;
import com.cbe.security.JwtService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "*")
public class AuthController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private WanafunziRepository wanafunziRepository;

    @Autowired
    private JwtService jwtService;

    @PostMapping("/login")
    public ResponseEntity<?> login(@Valid @RequestBody LoginRequest request) {
        User user = userRepository.findByUsername(request.getUsername())
                .orElseThrow(() -> new RuntimeException("Username au password si sahihi"));

        if (!user.getPassword().equals(request.getPassword())) {
            return ResponseEntity.status(401).body(Map.of("message", "Username au password si sahihi"));
        }

        if (!user.getActive()) {
            return ResponseEntity.status(403).body(Map.of("message", "Account yako imezimwa"));
        }

        String token = jwtService.generateToken(user.getUsername(), user.getRole().name());

        String jinaKamili = user.getUsername();
        if (user.getRole() == User.Role.MWANAFUNZI) {
            Wanafunzi w = wanafunziRepository.findByUserUsername(user.getUsername()).orElse(null);
            if (w != null) jinaKamili = w.getJinaKamili();
        }

        return ResponseEntity.ok(Map.of(
            "token", token,
            "username", user.getUsername(),
            "role", user.getRole().name(),
            "jinaKamili", jinaKamili
        ));
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody RegisterRequest request) {
        if (userRepository.existsByUsername(request.getUsername())) {
            return ResponseEntity.badRequest().body(Map.of("message", "Username imetumika"));
        }

        if (wanafunziRepository.findByNambaYaMwanafunzi(request.getNambaYaMwanafunzi()).isPresent()) {
            return ResponseEntity.badRequest().body(Map.of("message", "Namba ya mwanafunzi imetumika"));
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

        return ResponseEntity.ok(Map.of("message", "Usajili umefanikiwa!"));
    }
}