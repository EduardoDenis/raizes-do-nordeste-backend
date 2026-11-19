package br.com.raizesdonordeste.backend.api.auth.dto;

import br.com.raizesdonordeste.backend.domain.usuario.PerfilUsuario;

public class DadosCadastroUsuarioDTO {
    private String nome;
    private String email;
    private String senha;
    private PerfilUsuario perfil;

    // Getters e Setters
    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public String getSenha() { return senha; }
    public void setSenha(String senha) { this.senha = senha; }
    public PerfilUsuario getPerfil() { return perfil; }
    public void setPerfil(PerfilUsuario perfil) { this.perfil = perfil; }
}