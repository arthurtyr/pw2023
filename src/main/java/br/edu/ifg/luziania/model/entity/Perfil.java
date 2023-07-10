package br.edu.ifg.luziania.model.entity;

import javax.persistence.*;
import javax.validation.Constraint;

@Entity
@Table(uniqueConstraints={
        @UniqueConstraint(columnNames = {"id", "nome"})
})
public class Perfil{
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer id;
    private String nome;
    private String acessolog;
    private String excluirusuario;
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

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





}
