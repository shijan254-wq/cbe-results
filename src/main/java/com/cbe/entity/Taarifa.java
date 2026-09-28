package com.cbe.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "taarifa")
public class Taarifa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 200)
    private String kichwa;

    @Column(columnDefinition = "TEXT")
    private String maudhui;

    @Enumerated(EnumType.STRING)
    private Aina aina = Aina.TAARIFA;

    @Enumerated(EnumType.STRING)
    private Walengwa walengwa = Walengwa.WOTE;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "aliyeandika")
    private User aliyeandika;

    @Column(name = "tarehe")
    private LocalDateTime tarehe = LocalDateTime.now();

    public enum Aina { TAARIFA, MATANGAZO, ONYO, HABARI }
    public enum Walengwa { WOTE, WANAFUNZI, WALIMU, ADMIN }

    public Taarifa() {}

    public Long getId() { return id; }
    public String getKichwa() { return kichwa; }
    public String getMaudhui() { return maudhui; }
    public Aina getAina() { return aina; }
    public Walengwa getWalengwa() { return walengwa; }
    public User getAliyeandika() { return aliyeandika; }
    public LocalDateTime getTarehe() { return tarehe; }

    public void setId(Long id) { this.id = id; }
    public void setKichwa(String kichwa) { this.kichwa = kichwa; }
    public void setMaudhui(String maudhui) { this.maudhui = maudhui; }
    public void setAina(Aina aina) { this.aina = aina; }
    public void setWalengwa(Walengwa walengwa) { this.walengwa = walengwa; }
    public void setAliyeandika(User aliyeandika) { this.aliyeandika = aliyeandika; }
    public void setTarehe(LocalDateTime tarehe) { this.tarehe = tarehe; }
}