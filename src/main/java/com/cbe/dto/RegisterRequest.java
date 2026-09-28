package com.cbe.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class RegisterRequest {

    @NotBlank
    private String username;

    @NotBlank
    private String password;

    @NotBlank
    private String nambaYaMwanafunzi;

    @NotBlank
    private String jinaKamili;

    private String jinsia;

    private String course;

    private Integer mwakaWaKujiunga;

    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }
    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }
    public String getNambaYaMwanafunzi() { return nambaYaMwanafunzi; }
    public void setNambaYaMwanafunzi(String nambaYaMwanafunzi) { this.nambaYaMwanafunzi = nambaYaMwanafunzi; }
    public String getJinaKamili() { return jinaKamili; }
    public void setJinaKamili(String jinaKamili) { this.jinaKamili = jinaKamili; }
    public String getJinsia() { return jinsia; }
    public void setJinsia(String jinsia) { this.jinsia = jinsia; }
    public String getCourse() { return course; }
    public void setCourse(String course) { this.course = course; }
    public Integer getMwakaWaKujiunga() { return mwakaWaKujiunga; }
    public void setMwakaWaKujiunga(Integer mwakaWaKujiunga) { this.mwakaWaKujiunga = mwakaWaKujiunga; }
}