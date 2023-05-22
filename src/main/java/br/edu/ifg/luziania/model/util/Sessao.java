package br.edu.ifg.luziania.model.util;

import javax.enterprise.context.SessionScoped;
import java.util.List;
import java.util.ArrayList;

@SessionScoped
public class Sessao {

    private List<String> teste;

    public Sessao(){
        this.teste = new ArrayList<>();
    }
}
