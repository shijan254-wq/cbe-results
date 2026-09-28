package com.cbe.dto;

import java.time.LocalDateTime;

public class TaarifaDTO {

    private Long id;
    private String kichwa;
    private String maudhui;
    private String aina;
    private String walengwa;
    private String aliyeandika;
    private LocalDateTime tarehe;

    public TaarifaDTO() {}

    public TaarifaDTO(Long id, String kichwa, String maudhui, String aina,
                      String walengwa, String aliyeandika, LocalDateTime tarehe) {
        this.id = id;
        this.kichwa = kichwa;
        this.maudhui = maudhui;
        this.aina = aina;
        this.walengwa = walengwa;
        this.aliyeandika = aliyeandika;
        this.tarehe = tarehe;
    }

    public Long getId() { return id; }
    public String getKichwa() { return kichwa; }
    public String getMaudhui() { return maudhui; }
    public String getAina() { return aina; }
    public String getWalengwa() { return walengwa; }
    public String getAliyeandika() { return aliyeandika; }
    public LocalDateTime getTarehe() { return tarehe; }

    public void setId(Long id) { this.id = id; }
    public void setKichwa(String kichwa) { this.kichwa = kichwa; }
    public void setMaudhui(String maudhui) { this.maudhui = maudhui; }
    public void setAina(String aina) { this.aina = aina; }
    public void setWalengwa(String walengwa) { this.walengwa = walengwa; }
    public void setAliyeandika(String aliyeandika) { this.aliyeandika = aliyeandika; }
    public void setTarehe(LocalDateTime tarehe) { this.tarehe = tarehe; }
}