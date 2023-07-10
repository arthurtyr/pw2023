package br.edu.ifg.luziania.model.dao;

import br.edu.ifg.luziania.model.entity.Perfil;
import br.edu.ifg.luziania.model.entity.Registro;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;
import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;

@Dependent
public class RegistroDAO {
    @Inject
    EntityManager em;

    public void save(Registro entity){
        em.persist(entity);
    }

    public List<Registro> getLogs(){
        Query query = em.createQuery("from Registro");
        return query.getResultList();
    }
}
