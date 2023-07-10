package br.edu.ifg.luziania.model.dao;

import br.edu.ifg.luziania.model.entity.Usuario;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;

@Dependent
public class UsuarioDAO {

    @Inject
    EntityManager em;


    public void save(Usuario entity){
        em.persist(entity);
    }

    public Usuario getById(Integer id){
        Query query = em.createQuery("from Usuario where id = :id");
        query.setParameter("id", id);
        return (Usuario) query.getSingleResult();
    }

    public Usuario getByEmailAndSenha(String email, String senha){
        try{
            Query query = em.createQuery("from Usuario where email = :email and senha = :senha");
            query.setParameter("email", email);
            query.setParameter("senha", senha);
            return (Usuario) query.getSingleResult();
        }catch (NoResultException e){
            return null;
        }

    }

}