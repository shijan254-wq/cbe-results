package com.cbe.entity;

import jakarta.persistence.*;
import java.time.LocalTime;

@Entity
@Table(name = "ratiba")
public class Ratiba {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "kozi_id", nullable = false)
    private Kozi kozi;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Siku siku;

    @Column(name = "muda_wa_kwanza", nullable = false)
    private LocalTime mudaWaKwanza;

    @Column(name = "muda_wa_mwisho", nullable = false)
    private LocalTime mudaWaMwisho;

    private String darasa;
    private String mwalimu;

    public enum Siku { Jumatatu, Jumanne, Jumatano, Alhamisi, Ijumaa, Jumamosi }

    public Ratiba() {}

    public Long getId() { return id; }
    public Kozi getKozi() { return kozi; }
    public Siku getSiku() { return siku; }
    public LocalTime getMudaWaKwanza() { return mudaWaKwanza; }
    public LocalTime getMudaWaMwisho() { return mudaWaMwisho; }
    public String getDarasa() { return darasa; }
    public String getMwalimu() { return mwalimu; }

    public void setId(Long id) { this.id = id; }
    public void setKozi(Kozi kozi) { this.kozi = kozi; }
    public void setSiku(Siku siku) { this.siku = siku; }
    public void setMudaWaKwanza(LocalTime mudaWaKwanza) { this.mudaWaKwanza = mudaWaKwanza; }
    public void setMudaWaMwisho(LocalTime mudaWaMwisho) { this.mudaWaMwisho = mudaWaMwisho; }
    public void setDarasa(String darasa) { this.darasa = darasa; }
    public void setMwalimu(String mwalimu) { this.mwalimu = mwalimu; }
}