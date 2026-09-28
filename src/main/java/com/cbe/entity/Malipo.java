package com.cbe.entity;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "malipo")
public class Malipo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "mwanafunzi_id", nullable = false)
    private Wanafunzi mwanafunzi;

    @Column(nullable = false, precision = 12, scale = 2)
    private BigDecimal kiasi;

    @Column(name = "aina_ya_malipo", length = 100)
    private String ainaYaMalipo;

    @Column(name = "tarehe_ya_malipo")
    private LocalDate tareheYaMalipo;

    @Column(unique = true, length = 50)
    private String risiti;

    @Enumerated(EnumType.STRING)
    @Column(length = 20)
    private Hali hali = Hali.HAIJALIPWA;

    @Column(columnDefinition = "TEXT")
    private String maelezo;

    @Column(name = "created_at")
    private LocalDateTime createdAt = LocalDateTime.now();

    public enum Hali { IMELIPWA, HAIJALIPWA, NUSU }

    public Malipo() {}

    public Long getId() { return id; }
    public Wanafunzi getMwanafunzi() { return mwanafunzi; }
    public BigDecimal getKiasi() { return kiasi; }
    public String getAinaYaMalipo() { return ainaYaMalipo; }
    public LocalDate getTareheYaMalipo() { return tareheYaMalipo; }
    public String getRisiti() { return risiti; }
    public Hali getHali() { return hali; }
    public String getMaelezo() { return maelezo; }
    public LocalDateTime getCreatedAt() { return createdAt; }

    public void setId(Long id) { this.id = id; }
    public void setMwanafunzi(Wanafunzi mwanafunzi) { this.mwanafunzi = mwanafunzi; }
    public void setKiasi(BigDecimal kiasi) { this.kiasi = kiasi; }
    public void setAinaYaMalipo(String ainaYaMalipo) { this.ainaYaMalipo = ainaYaMalipo; }
    public void setTareheYaMalipo(LocalDate tareheYaMalipo) { this.tareheYaMalipo = tareheYaMalipo; }
    public void setRisiti(String risiti) { this.risiti = risiti; }
    public void setHali(Hali hali) { this.hali = hali; }
    public void setMaelezo(String maelezo) { this.maelezo = maelezo; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }
}