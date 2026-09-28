package com.cbe.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

public class MalipoDTO {

    private Long id;
    private String jinaKamili;
    private String nambaYaMwanafunzi;
    private BigDecimal kiasi;
    private String ainaYaMalipo;
    private LocalDate tareheYaMalipo;
    private String risiti;
    private String hali;
    private String maelezo;

    public MalipoDTO() {}

    public MalipoDTO(Long id, String jinaKamili, String nambaYaMwanafunzi,
                     BigDecimal kiasi, String ainaYaMalipo, LocalDate tareheYaMalipo,
                     String risiti, String hali, String maelezo) {
        this.id = id;
        this.jinaKamili = jinaKamili;
        this.nambaYaMwanafunzi = nambaYaMwanafunzi;
        this.kiasi = kiasi;
        this.ainaYaMalipo = ainaYaMalipo;
        this.tareheYaMalipo = tareheYaMalipo;
        this.risiti = risiti;
        this.hali = hali;
        this.maelezo = maelezo;
    }

    public Long getId() { return id; }
    public String getJinaKamili() { return jinaKamili; }
    public String getNambaYaMwanafunzi() { return nambaYaMwanafunzi; }
    public BigDecimal getKiasi() { return kiasi; }
    public String getAinaYaMalipo() { return ainaYaMalipo; }
    public LocalDate getTareheYaMalipo() { return tareheYaMalipo; }
    public String getRisiti() { return risiti; }
    public String getHali() { return hali; }
    public String getMaelezo() { return maelezo; }

    public void setId(Long id) { this.id = id; }
    public void setJinaKamili(String jinaKamili) { this.jinaKamili = jinaKamili; }
    public void setNambaYaMwanafunzi(String nambaYaMwanafunzi) { this.nambaYaMwanafunzi = nambaYaMwanafunzi; }
    public void setKiasi(BigDecimal kiasi) { this.kiasi = kiasi; }
    public void setAinaYaMalipo(String ainaYaMalipo) { this.ainaYaMalipo = ainaYaMalipo; }
    public void setTareheYaMalipo(LocalDate tareheYaMalipo) { this.tareheYaMalipo = tareheYaMalipo; }
    public void setRisiti(String risiti) { this.risiti = risiti; }
    public void setHali(String hali) { this.hali = hali; }
    public void setMaelezo(String maelezo) { this.maelezo = maelezo; }
}