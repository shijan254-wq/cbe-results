package com.cbe.service;

import com.cbe.dto.LoginRequest;
import com.cbe.dto.LoginResponse;
import com.cbe.entity.User;
import com.cbe.entity.Wanafunzi;
import com.cbe.exception.ResourceNotFoundException;
import com.cbe.repository.UserRepository;
import com.cbe.repository.WanafunziRepository;
import com.cbe.security.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private WanafunziRepository wanafunziRepository;

    @Autowired
    private JwtService jwtService;

    public LoginResponse login(LoginRequest request) {
        User user = userRepository.findByUsername(request.getUsername())
                .orElseThrow(() -> new ResourceNotFoundException("Username au password si sahihi"));

        if (!user.getPassword().equals(request.getPassword())) {
            throw new ResourceNotFoundException("Username au password si sahihi");
        }

        if (!user.getActive()) {
            throw new RuntimeException("Account yako imezimwa");
        }

        String token = jwtService.generateToken(user.getUsername(), user.getRole().name());

        String jinaKamili = user.getUsername();
        if (user.getRole() == User.Role.MWANAFUNZI) {
            Wanafunzi w = wanafunziRepository.findByUserUsername(user.getUsername()).orElse(null);
            if (w != null) jinaKamili = w.getJinaKamili();
        }

        return new LoginResponse(token, user.getUsername(), user.getRole().name(), jinaKamili);
    }
}