package br.edu.ifg.luziania.model.dto;

import br.edu.ifg.luziania.model.entity.Perfil;

import java.util.List;

public class PerfilRetornoDTO {

    private Integer status;

    private String mensagem;

    private List<Perfil> entity;
    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getMensagem() {
        return mensagem;
    }

    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }

    public List<Perfil> getEntity() {
        return entity;
    }

    public void setEntity(List<Perfil> entity) {
        this.entity = entity;
    }

}