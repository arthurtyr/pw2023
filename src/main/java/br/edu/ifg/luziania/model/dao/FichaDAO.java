package br.edu.ifg.luziania.model.dao;

import br.edu.ifg.luziania.model.entity.Ficha;
import br.edu.ifg.luziania.model.entity.Perfil;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;
import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;

@Dependent
public class FichaDAO {
    @Inject
    EntityManager em;

    public void save(Ficha entity){
        em.persist(entity);
    }

    public Ficha getById(Integer id){
        Query query = em.createQuery("from Ficha where id = :id");
        query.setParameter("id", id);
        return (Ficha) query.getSingleResult();
    }

    public Ficha getByNome(String nome){
        Query query = em.createQuery("from Ficha where nome = :nome");
        query.setParameter("nome", nome);
        return (Ficha) query.getSingleResult();
    }
    public List<Ficha> getFichasdeUsuario(String usuario){
        Query query = em.createQuery("from Ficha where usuario = :usuario");
        query.setParameter("usuario", usuario);
        return query.getResultList();
    }
}
