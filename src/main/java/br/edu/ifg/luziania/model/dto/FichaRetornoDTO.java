package br.edu.ifg.luziania.model.dto;

import br.edu.ifg.luziania.model.entity.Ficha;

import java.util.List;

public class FichaRetornoDTO {

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

    public List<Ficha> getEntity() {
        return entity;
    }

    public void setEntity(List<Ficha> entity) {
        this.entity = entity;
    }

    private Integer status;
    private String mensagem;
    private List<Ficha> entity ;
}
