package com.shubham.todolistapp.entity;

public class JwtResponse {

    private String jwt;


    public JwtResponse(String jwt) {
        this.jwt = jwt;
    }

    public String getJwt() {
        return jwt;
    }
}
