package br.edu.ifg.luziania.model.dto;

public class PerfilDTO {

    private String nomeperfil;

    private String trocanomeperfil;

    private String acessolog;

    private String criarficha;

    public String getNomeperfil() {
        return nomeperfil;
    }

    public void setNomeperfil(String nomeperfil) {
        this.nomeperfil = nomeperfil;
    }

    public String getTrocanomeperfil() {
        return trocanomeperfil;
    }

    public void setTrocanomeperfil(String trocanomeperfil) {
        this.trocanomeperfil = trocanomeperfil;
    }

    public String getAcessolog() {
        return acessolog;
    }

    public void setAcessolog(String acessolog) {
        this.acessolog = acessolog;
    }

    public String getCriarficha() {
        return criarficha;
    }

    public void setCriarficha(String criarficha) {
        this.criarficha = criarficha;
    }
}
