/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.champ.Generico;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author andre
 */
public class ServicoGenerico<T> {
    
    private Class<T> entity;
    @PersistenceContext(unitName = "PhbChampPU")
    EntityManager entityManager;

    public ServicoGenerico(Class<T> entity) {
        this.entity = entity;
        
        

    }

    public void save(T entity) {
        getEntityManager().persist(entity);
    }

    public void delete(T entity) {
//        update(entity);

        T entityToBeRemoved = entityManager.merge(entity);
        entityManager.remove(entityToBeRemoved);
    }
    
    public void remove(T entity){
        update(entity);
    }

    public void update(T entity) {
        getEntityManager().merge(entity);
        getEntityManager().flush();
    }

    public T find(Long entityID) {
        T objeto = getEntityManager().find(entity, entityID);
        getEntityManager().refresh(objeto);
        return objeto;
    }

//    public List<T> findAll(Usuario usuario) {
//        return getEntityManager().createQuery("select e from " + entity.getSimpleName() + " e JOIN Usuario u where e.ativo = true AND u.id = " + usuario.getId()).getResultList();
//    }

//    public List<T> findAll() {
//        return getEntityManager().createQuery("select e from " + entity.getSimpleName() + " e where e.ativo = true").getResultList();
//    }
//
//    public Long quantidadeRegistros() {
//        Long result = (Long) getEntityManager().createQuery("select count(e) from " + entity.getSimpleName() + " e").getSingleResult();
//
//        return result != null ? result : 0;
//    }

//    public List<T> find(T t, Integer first, Integer pageSize) {
//        String sql = "select e from " + entity.getSimpleName() + " e ";
//
//        Query query = getEntityManager().createQuery(sql);
//
//        if (first != null) {
//            query.setFirstResult(first);
//        }
//
//        if (pageSize != null) {
//            query.setMaxResults(pageSize);
//        }
//
//        return query.getResultList();
//    }
//
//    /**
//     * Resgata usu�rio logado no sistema
//     *
//     * @return
//     */
//    public Usuario verifySystemUserForLogin() {
//        try {
//            String nome = FacesContext.getCurrentInstance().getExternalContext().getUserPrincipal().getName();
//            if (nome != null && !nome.isEmpty()) {
//                Usuario usr;
//                final String sql = "select u from " + Usuario.class.getSimpleName() + " u where "
//                        + "u.login like :nome and u.ativo = true";
//                Query query = getEntityManager().createQuery(sql);
//                query.setParameter("nome", nome);
//                usr = (Usuario) query.getSingleResult();
//
//                return usr;
//            }
//        } catch (Exception e) {
////            Logger.getLogger(ServicoGenerico.class.getName()).log(Level.SEVERE, null, e);
////
////            System.err.println(e);
//        }
//        return null;
//    }

    public EntityManager getEntityManager() {
        return entityManager;
    }

    public void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }
    
}

