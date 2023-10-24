package com.itlize.korera.Security;

import java.util.Objects;

public class AuthenticationResponse {

    private String token;

    public AuthenticationResponse(String token){
        this.token  = token;
    }
    public AuthenticationResponse(){

    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof AuthenticationResponse that)) return false;
        return Objects.equals(getToken(), that.getToken());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getToken());
    }

    @Override
    public String toString() {
        return "AuthenticationResponse{" +
                "token='" + token + '\'' +
                '}';
    }
}
