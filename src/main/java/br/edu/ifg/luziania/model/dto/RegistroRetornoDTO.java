package br.edu.ifg.luziania.model.dto;
import java.util.List;
import br.edu.ifg.luziania.model.entity.Registro;

public class RegistroRetornoDTO {
    private Integer status;
    private String mensagem;
    private List<Registro> entity;
    public List<Registro> getEntity() {
        return entity;
    }

    public void setEntity(List<Registro> entity) {
        this.entity = entity;
    }
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


}
