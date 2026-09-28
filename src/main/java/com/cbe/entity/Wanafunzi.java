package com.cbe.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "wanafunzi")
public class Wanafunzi {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", unique = true, nullable = false)
    private User user;

    @Column(name = "namba_ya_mwanafunzi", unique = true, nullable = false, length = 20)
    private String nambaYaMwanafunzi;

    @Column(name = "jina_kamili", nullable = false, length = 100)
    private String jinaKamili;

    @Column(length = 1)
    private String jinsia;

    @Column(length = 100)
    private String course;

    @Column(name = "mwaka_wa_kujiunga")
    private Integer mwakaWaKujiunga;

    public Wanafunzi() {}
    public Long getId() { return id; }
    public User getUser() { return user; }
    public String getNambaYaMwanafunzi() { return nambaYaMwanafunzi; }
    public String getJinaKamili() { return jinaKamili; }
    public String getJinsia() { return jinsia; }
    public String getCourse() { return course; }
    public Integer getMwakaWaKujiunga() { return mwakaWaKujiunga; }

    public void setId(Long id) { this.id = id; }
    public void setUser(User user) { this.user = user; }
    public void setNambaYaMwanafunzi(String n) { this.nambaYaMwanafunzi = n; }
    public void setJinaKamili(String j) { this.jinaKamili = j; }
    public void setJinsia(String j) { this.jinsia = j; }
    public void setCourse(String c) { this.course = c; }
    public void setMwakaWaKujiunga(Integer m) { this.mwakaWaKujiunga = m; }
}