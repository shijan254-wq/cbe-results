package com.cbe.entity;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "mahudhurio")
public class Mahudhurio {

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
    private LocalDate tarehe;

    @Enumerated(EnumType.STRING)
    private Hali hali = Hali.ALIYEPO;

    @Column(columnDefinition = "TEXT")
    private String maelezo;

    public enum Hali { ALIYEPO, HAKUWEPO, ALIYECHELEWA }

    public Mahudhurio() {}

    public Long getId() { return id; }
    public Wanafunzi getMwanafunzi() { return mwanafunzi; }
    public Kozi getKozi() { return kozi; }
    public LocalDate getTarehe() { return tarehe; }
    public Hali getHali() { return hali; }
    public String getMaelezo() { return maelezo; }

    public void setId(Long id) { this.id = id; }
    public void setMwanafunzi(Wanafunzi mwanafunzi) { this.mwanafunzi = mwanafunzi; }
    public void setKozi(Kozi kozi) { this.kozi = kozi; }
    public void setTarehe(LocalDate tarehe) { this.tarehe = tarehe; }
    public void setHali(Hali hali) { this.hali = hali; }
    public void setMaelezo(String maelezo) { this.maelezo = maelezo; }
}