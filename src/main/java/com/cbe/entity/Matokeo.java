package com.cbe.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "matokeo")
public class Matokeo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "mwanafunzi_id", nullable = false)
    private Wanafunzi mwanafunzi;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "kozi_id", nullable = false)
    private Kozi kozi;

    @Column(nullable = false)
    private Integer alama;

    @Column(length = 2)
    private String grade;

    private Integer mwaka;

    @Column(length = 20)
    private String semester;

    @Column(name = "tarehe")
    private LocalDateTime tarehe = LocalDateTime.now();

    public Matokeo() {}
    public Long getId() { return id; }
    public Wanafunzi getMwanafunzi() { return mwanafunzi; }
    public Kozi getKozi() { return kozi; }
    public Integer getAlama() { return alama; }
    public String getGrade() { return grade; }
    public Integer getMwaka() { return mwaka; }
    public String getSemester() { return semester; }
    public LocalDateTime getTarehe() { return tarehe; }

    public void setId(Long id) { this.id = id; }
    public void setMwanafunzi(Wanafunzi m) { this.mwanafunzi = m; }
    public void setKozi(Kozi k) { this.kozi = k; }
    public void setAlama(Integer a) { this.alama = a; }
    public void setGrade(String g) { this.grade = g; }
    public void setMwaka(Integer m) { this.mwaka = m; }
    public void setSemester(String s) { this.semester = s; }
    public void setTarehe(LocalDateTime t) { this.tarehe = t; }
}