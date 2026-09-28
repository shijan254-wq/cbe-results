package com.cbe.dto;

import java.time.LocalDate;

public class MahudhurioDTO {

    private Long id;
    private String jinaKamili;
    private String nambaYaMwanafunzi;
    private String code;
    private String jinaLaKozi;
    private LocalDate tarehe;
    private String hali;
    private String maelezo;

    public MahudhurioDTO() {}

    public MahudhurioDTO(Long id, String jinaKamili, String nambaYaMwanafunzi,
                         String code, String jinaLaKozi, LocalDate tarehe,
                         String hali, String maelezo) {
        this.id = id;
        this.jinaKamili = jinaKamili;
        this.nambaYaMwanafunzi = nambaYaMwanafunzi;
        this.code = code;
        this.jinaLaKozi = jinaLaKozi;
        this.tarehe = tarehe;
        this.hali = hali;
        this.maelezo = maelezo;
    }

    public Long getId() { return id; }
    public String getJinaKamili() { return jinaKamili; }
    public String getNambaYaMwanafunzi() { return nambaYaMwanafunzi; }
    public String getCode() { return code; }
    public String getJinaLaKozi() { return jinaLaKozi; }
    public LocalDate getTarehe() { return tarehe; }
    public String getHali() { return hali; }
    public String getMaelezo() { return maelezo; }

    public void setId(Long id) { this.id = id; }
    public void setJinaKamili(String jinaKamili) { this.jinaKamili = jinaKamili; }
    public void setNambaYaMwanafunzi(String nambaYaMwanafunzi) { this.nambaYaMwanafunzi = nambaYaMwanafunzi; }
    public void setCode(String code) { this.code = code; }
    public void setJinaLaKozi(String jinaLaKozi) { this.jinaLaKozi = jinaLaKozi; }
    public void setTarehe(LocalDate tarehe) { this.tarehe = tarehe; }
    public void setHali(String hali) { this.hali = hali; }
    public void setMaelezo(String maelezo) { this.maelezo = maelezo; }
}