package br.com.raizesdonordeste.backend.api.auth.dto;

public class TokenResponseDTO {
    private String accessToken;

    public TokenResponseDTO(String accessToken) {
        this.accessToken = accessToken;
    }

    public String getAccessToken() { return accessToken; }
}