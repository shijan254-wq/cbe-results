package com.cbe.dto;

public class RatibaDTO {

    private Long id;
    private String code;
    private String jinaLaKozi;
    private String siku;
    private String mudaWaKwanza;
    private String mudaWaMwisho;
    private String darasa;
    private String mwalimu;

    public RatibaDTO() {}

    public RatibaDTO(Long id, String code, String jinaLaKozi, String siku,
                     String mudaWaKwanza, String mudaWaMwisho, String darasa, String mwalimu) {
        this.id = id;
        this.code = code;
        this.jinaLaKozi = jinaLaKozi;
        this.siku = siku;
        this.mudaWaKwanza = mudaWaKwanza;
        this.mudaWaMwisho = mudaWaMwisho;
        this.darasa = darasa;
        this.mwalimu = mwalimu;
    }

    public Long getId() { return id; }
    public String getCode() { return code; }
    public String getJinaLaKozi() { return jinaLaKozi; }
    public String getSiku() { return siku; }
    public String getMudaWaKwanza() { return mudaWaKwanza; }
    public String getMudaWaMwisho() { return mudaWaMwisho; }
    public String getDarasa() { return darasa; }
    public String getMwalimu() { return mwalimu; }

    public void setId(Long id) { this.id = id; }
    public void setCode(String code) { this.code = code; }
    public void setJinaLaKozi(String jinaLaKozi) { this.jinaLaKozi = jinaLaKozi; }
    public void setSiku(String siku) { this.siku = siku; }
    public void setMudaWaKwanza(String mudaWaKwanza) { this.mudaWaKwanza = mudaWaKwanza; }
    public void setMudaWaMwisho(String mudaWaMwisho) { this.mudaWaMwisho = mudaWaMwisho; }
    public void setDarasa(String darasa) { this.darasa = darasa; }
    public void setMwalimu(String mwalimu) { this.mwalimu = mwalimu; }
}