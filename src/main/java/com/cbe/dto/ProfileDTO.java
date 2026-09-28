package com.cbe.dto;

public class ProfileDTO {

    private Long id;
    private String username;
    private String jinaKamili;
    private String nambaYaMwanafunzi;
    private String jinsia;
    private String course;
    private Integer mwakaWaKujiunga;
    private String role;

    public ProfileDTO() {}

    public ProfileDTO(Long id, String username, String jinaKamili, String nambaYaMwanafunzi,
                      String jinsia, String course, Integer mwakaWaKujiunga, String role) {
        this.id = id;
        this.username = username;
        this.jinaKamili = jinaKamili;
        this.nambaYaMwanafunzi = nambaYaMwanafunzi;
        this.jinsia = jinsia;
        this.course = course;
        this.mwakaWaKujiunga = mwakaWaKujiunga;
        this.role = role;
    }

    public Long getId() { return id; }
    public String getUsername() { return username; }
    public String getJinaKamili() { return jinaKamili; }
    public String getNambaYaMwanafunzi() { return nambaYaMwanafunzi; }
    public String getJinsia() { return jinsia; }
    public String getCourse() { return course; }
    public Integer getMwakaWaKujiunga() { return mwakaWaKujiunga; }
    public String getRole() { return role; }

    public void setId(Long id) { this.id = id; }
    public void setUsername(String username) { this.username = username; }
    public void setJinaKamili(String jinaKamili) { this.jinaKamili = jinaKamili; }
    public void setNambaYaMwanafunzi(String nambaYaMwanafunzi) { this.nambaYaMwanafunzi = nambaYaMwanafunzi; }
    public void setJinsia(String jinsia) { this.jinsia = jinsia; }
    public void setCourse(String course) { this.course = course; }
    public void setMwakaWaKujiunga(Integer mwakaWaKujiunga) { this.mwakaWaKujiunga = mwakaWaKujiunga; }
    public void setRole(String role) { this.role = role; }
}