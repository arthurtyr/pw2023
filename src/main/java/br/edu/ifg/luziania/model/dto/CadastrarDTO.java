package br.edu.ifg.luziania.model.dto;

public class CadastrarDTO {
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String usuario) {
        this.nome = usuario;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getConfisenha() {
        return confisenha;
    }

    public void setConfisenha(String confisenha) {
        this.confisenha = confisenha;
    }

    private String email;
    private String nome;
    private String senha;
    private String confisenha;


}
