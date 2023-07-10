package br.edu.ifg.luziania.model.dto;

public class CadastroPerfilDTO {

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getAcessolog() {
        return acessolog;
    }

    public void setAcessolog(String acessolog) {
        this.acessolog = acessolog;
    }

    public String getExcluirusuario() {
        return excluirusuario;
    }

    public void setExcluirusuario(String excluirusuario) {
        this.excluirusuario = excluirusuario;
    }

    private String nome;

    private String acessolog;

    private String excluirusuario;


}
