package br.edu.ifg.luziania.model.dao;

import br.edu.ifg.luziania.model.entity.Perfil;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;

@Dependent
public class PerfilDAO {

    @Inject
    EntityManager em;


    public void save(Perfil entity){
        em.persist(entity);
    }

    public Perfil getById(Integer id){
        Query query = em.createQuery("from Perfil where id = :id");
        query.setParameter("id", id);
        return (Perfil) query.getSingleResult();
    }

    public Perfil getByNome(String nome){
        Query query = em.createQuery("from Perfil where nome = :nome");
        query.setParameter("nome", nome);
        return (Perfil) query.getSingleResult();
    }
    public List<Perfil> getPerfis(){
        Query query = em.createQuery("from Perfil");
        return query.getResultList();
    }

}