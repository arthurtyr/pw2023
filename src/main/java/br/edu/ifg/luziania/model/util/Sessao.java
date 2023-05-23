package br.edu.ifg.luziania.model.util;

import javax.enterprise.context.SessionScoped;
import java.util.List;
import java.util.ArrayList;

@SessionScoped
public class Sessao {

    private String nome;
    private List<String> permissoes;

    public Sessao(){
        this.permissoes = new ArrayList<>();
        this.nome = "";
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public List<String> getPermissoes() {
        return permissoes;
    }

    public void setPermissoes(List<String> permissoes) {
        this.permissoes = permissoes;
    }


}
