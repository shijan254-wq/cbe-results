package com.cbe.controller;

import com.cbe.dto.*;
import com.cbe.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.io.ByteArrayInputStream;
import java.util.List;

@RestController
@RequestMapping("/api/export")
@CrossOrigin(origins = "*")
public class ExportController {

    @Autowired
    private CsvService csvService;

    @Autowired
    private MatokeoService matokeoService;

    @Autowired
    private MalipoService malipoService;

    @Autowired
    private AdminService adminService;

    @Autowired
    private MahudhurioService mahudhurioService;

    @GetMapping("/matokeo")
    @PreAuthorize("hasAnyRole('MWALIMU', 'ADMIN')")
    public ResponseEntity<InputStreamResource> exportMatokeo() {
        List<MatokeoDTO> data = matokeoService.pataMatokeoYote();
        ByteArrayInputStream csv = csvService.matokeoToCsv(data);
        return csvResponse(csv, "matokeo.csv");
    }

    @GetMapping("/matokeo-yangu")
    @PreAuthorize("hasRole('MWANAFUNZI')")
    public ResponseEntity<InputStreamResource> exportMatokeoYangu(Authentication auth) {
        List<MatokeoDTO> data = matokeoService.pataMatokeoYaMwanafunzi(auth.getName());
        ByteArrayInputStream csv = csvService.matokeoToCsv(data);
        return csvResponse(csv, "matokeo-yangu.csv");
    }

    @GetMapping("/wanafunzi")
    @PreAuthorize("hasAnyRole('MWALIMU', 'ADMIN')")
    public ResponseEntity<InputStreamResource> exportWanafunzi() {
        List<ProfileDTO> data = adminService.wanafunziWote();
        ByteArrayInputStream csv = csvService.wanafunziToCsv(data);
        return csvResponse(csv, "wanafunzi.csv");
    }

    @GetMapping("/malipo")
    @PreAuthorize("hasAnyRole('MWALIMU', 'ADMIN')")
    public ResponseEntity<InputStreamResource> exportMalipo() {
        List<MalipoDTO> data = malipoService.pataMalipoYote();
        ByteArrayInputStream csv = csvService.malipoToCsv(data);
        return csvResponse(csv, "malipo.csv");
    }

    @GetMapping("/mahudhurio")
    @PreAuthorize("hasAnyRole('MWALIMU', 'ADMIN')")
    public ResponseEntity<InputStreamResource> exportMahudhurio() {
        List<MahudhurioDTO> data = mahudhurioService.pataMahudhurioYote();
        ByteArrayInputStream csv = csvService.mahudhurioToCsv(data);
        return csvResponse(csv, "mahudhurio.csv");
    }

    private ResponseEntity<InputStreamResource> csvResponse(ByteArrayInputStream csv, String filename) {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition", "attachment; filename=" + filename);
        return ResponseEntity
                .ok()
                .headers(headers)
                .contentType(MediaType.parseMediaType("text/csv"))
                .body(new InputStreamResource(csv));
    }
}