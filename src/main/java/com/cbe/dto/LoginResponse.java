package com.cbe.dto;

public class LoginResponse {

    private String token;
    private String username;
    private String role;
    private String jinaKamili;

    public LoginResponse(String token, String username, String role, String jinaKamili) {
        this.token = token;
        this.username = username;
        this.role = role;
        this.jinaKamili = jinaKamili;
    }

    public String getToken() { return token; }
    public String getUsername() { return username; }
    public String getRole() { return role; }
    public String getJinaKamili() { return jinaKamili; }
}