package com.cbe.controller;

import com.cbe.dto.MatokeoDTO;
import com.cbe.dto.ProfileDTO;
import com.cbe.service.MatokeoService;
import com.cbe.service.PdfService;
import com.cbe.service.ProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.io.ByteArrayInputStream;
import java.util.List;

@RestController
@RequestMapping("/api/matokeo")
@CrossOrigin(origins = "*")
public class MatokeoController {

    @Autowired
    private MatokeoService matokeoService;

    @Autowired
    private ProfileService profileService;

    @Autowired
    private PdfService pdfService;

    @GetMapping("/yangu")
    public ResponseEntity<List<MatokeoDTO>> pataMatokeoYangu(Authentication auth) {
        return ResponseEntity.ok(matokeoService.pataMatokeoYaMwanafunzi(auth.getName()));
    }

    @GetMapping("/yote")
    public ResponseEntity<List<MatokeoDTO>> pataMatokeoYote() {
        return ResponseEntity.ok(matokeoService.pataMatokeoYote());
    }

    @GetMapping("/tafuta")
    public ResponseEntity<List<MatokeoDTO>> tafuta(@RequestParam String namba) {
        return ResponseEntity.ok(matokeoService.tafuta(namba));
    }

    @GetMapping("/pdf")
    public ResponseEntity<InputStreamResource> pakuaPdf(Authentication auth) {
        ProfileDTO profile = profileService.pataProfile(auth.getName());
        List<MatokeoDTO> matokeo = matokeoService.pataMatokeoYaMwanafunzi(auth.getName());

        ByteArrayInputStream pdf = pdfService.generateMatokeoPdf(profile, matokeo);

        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition", "inline; filename=matokeo-" + auth.getName() + ".pdf");

        return ResponseEntity
                .ok()
                .headers(headers)
                .contentType(MediaType.APPLICATION_PDF)
                .body(new InputStreamResource(pdf));
    }
}