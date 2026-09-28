package com.cbe.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "kozi")
public class Kozi {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false, length = 20)
    private String code;

    @Column(name = "jina_la_kozi", nullable = false, length = 150)
    private String jinaLaKozi;

    private Integer credits = 3;

    @Column(length = 20)
    private String semester;

    public Kozi() {}
    public Long getId() { return id; }
    public String getCode() { return code; }
    public String getJinaLaKozi() { return jinaLaKozi; }
    public Integer getCredits() { return credits; }
    public String getSemester() { return semester; }

    public void setId(Long id) { this.id = id; }
    public void setCode(String c) { this.code = c; }
    public void setJinaLaKozi(String j) { this.jinaLaKozi = j; }
    public void setCredits(Integer c) { this.credits = c; }
    public void setSemester(String s) { this.semester = s; }
}