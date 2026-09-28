package com.cbe.dto;

import java.math.BigDecimal;

public class TakwimuDTO {

    private Long jumlaWanafunzi;
    private Long jumlaWalimu;
    private Long jumlaKozi;
    private Long jumlaMatokeo;
    private Long jumlaMalipo;
    private BigDecimal jumlaKiasi;
    private Double wastaniAlama;
    private Long wanafunziWaliolipa;
    private Long wanafunziHawajalipa;

    public TakwimuDTO() {}

    public Long getJumlaWanafunzi() { return jumlaWanafunzi; }
    public Long getJumlaWalimu() { return jumlaWalimu; }
    public Long getJumlaKozi() { return jumlaKozi; }
    public Long getJumlaMatokeo() { return jumlaMatokeo; }
    public Long getJumlaMalipo() { return jumlaMalipo; }
    public BigDecimal getJumlaKiasi() { return jumlaKiasi; }
    public Double getWastaniAlama() { return wastaniAlama; }
    public Long getWanafunziWaliolipa() { return wanafunziWaliolipa; }
    public Long getWanafunziHawajalipa() { return wanafunziHawajalipa; }

    public void setJumlaWanafunzi(Long jumlaWanafunzi) { this.jumlaWanafunzi = jumlaWanafunzi; }
    public void setJumlaWalimu(Long jumlaWalimu) { this.jumlaWalimu = jumlaWalimu; }
    public void setJumlaKozi(Long jumlaKozi) { this.jumlaKozi = jumlaKozi; }
    public void setJumlaMatokeo(Long jumlaMatokeo) { this.jumlaMatokeo = jumlaMatokeo; }
    public void setJumlaMalipo(Long jumlaMalipo) { this.jumlaMalipo = jumlaMalipo; }
    public void setJumlaKiasi(BigDecimal jumlaKiasi) { this.jumlaKiasi = jumlaKiasi; }
    public void setWastaniAlama(Double wastaniAlama) { this.wastaniAlama = wastaniAlama; }
    public void setWanafunziWaliolipa(Long wanafunziWaliolipa) { this.wanafunziWaliolipa = wanafunziWaliolipa; }
    public void setWanafunziHawajalipa(Long wanafunziHawajalipa) { this.wanafunziHawajalipa = wanafunziHawajalipa; }
}