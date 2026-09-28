package com.cbe.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class ChangePasswordRequest {

    @NotBlank
    private String passwordYaSasa;

    @NotBlank
    @Size(min = 6, message = "Password lazima iwe na herufi 6 au zaidi")
    private String passwordMpya;

    public String getPasswordYaSasa() { return passwordYaSasa; }
    public void setPasswordYaSasa(String passwordYaSasa) { this.passwordYaSasa = passwordYaSasa; }
    public String getPasswordMpya() { return passwordMpya; }
    public void setPasswordMpya(String passwordMpya) { this.passwordMpya = passwordMpya; }
}