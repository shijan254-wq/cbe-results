package com.cbe.dto;

import java.time.LocalDateTime;

public class MatokeoDTO {

    private Long id;
    private String code;
    private String jinaLaKozi;
    private Integer credits;
    private Integer alama;
    private String grade;
    private Integer mwaka;
    private String semester;
    private LocalDateTime tarehe;
    private String jinaKamili;

    public MatokeoDTO() {}

    public MatokeoDTO(Long id, String code, String jinaLaKozi, Integer credits,
                      Integer alama, String grade, Integer mwaka, String semester,
                      LocalDateTime tarehe) {
        this.id = id;
        this.code = code;
        this.jinaLaKozi = jinaLaKozi;
        this.credits = credits;
        this.alama = alama;
        this.grade = grade;
        this.mwaka = mwaka;
        this.semester = semester;
        this.tarehe = tarehe;
    }

    public Long getId() { return id; }
    public String getCode() { return code; }
    public String getJinaLaKozi() { return jinaLaKozi; }
    public Integer getCredits() { return credits; }
    public Integer getAlama() { return alama; }
    public String getGrade() { return grade; }
    public Integer getMwaka() { return mwaka; }
    public String getSemester() { return semester; }
    public LocalDateTime getTarehe() { return tarehe; }
    public String getJinaKamili() { return jinaKamili; }

    public void setId(Long id) { this.id = id; }
    public void setCode(String code) { this.code = code; }
    public void setJinaLaKozi(String jinaLaKozi) { this.jinaLaKozi = jinaLaKozi; }
    public void setCredits(Integer credits) { this.credits = credits; }
    public void setAlama(Integer alama) { this.alama = alama; }
    public void setGrade(String grade) { this.grade = grade; }
    public void setMwaka(Integer mwaka) { this.mwaka = mwaka; }
    public void setSemester(String semester) { this.semester = semester; }
    public void setTarehe(LocalDateTime tarehe) { this.tarehe = tarehe; }
    public void setJinaKamili(String jinaKamili) { this.jinaKamili = jinaKamili; }
}