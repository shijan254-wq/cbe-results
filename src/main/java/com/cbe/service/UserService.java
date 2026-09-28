package com.cbe.service;

import com.cbe.dto.ChangePasswordRequest;
import com.cbe.entity.User;
import com.cbe.exception.ResourceNotFoundException;
import com.cbe.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public void badilishaPassword(String username, ChangePasswordRequest request) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new ResourceNotFoundException("Mtumiaji hajapatikana"));

        if (!user.getPassword().equals(request.getPasswordYaSasa())) {
            throw new RuntimeException("Password ya sasa si sahihi");
        }

        user.setPassword(request.getPasswordMpya());
        userRepository.save(user);
    }

    public void zimwaMtumiaji(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("Mtumiaji hajapatikana"));
        user.setActive(false);
        userRepository.save(user);
    }

    public void washaMtumiaji(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("Mtumiaji hajapatikana"));
        user.setActive(true);
        userRepository.save(user);
    }
}