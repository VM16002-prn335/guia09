/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.edu.uesocc.ingenieria.prn335_2017.datos.acceso;

import java.util.List;
import javax.persistence.EntityManager;

/**
 *
 * @author bryan
 */
public abstract class AbstractFacade<T> {

    private Class<T> entityClass;

    public AbstractFacade(Class<T> entityClass) {
        this.entityClass = entityClass;
    }
    
    /**
     * 
     * @return 
     */
    protected abstract EntityManager getEntityManager();
    
    /**
     * 
     * @param entity 
     */
    public void create(T entity) {
        EntityManager em = getEntityManager();
        try {
            if (em != null && entity != null) {
                em.persist(entity);
            }else{
            System.out.println("algo es nulo");
            }
        } catch (Exception e) {
            System.out.println("ERROR: " + e);
        }
    }
    
    /**
     * 
     * @param entity 
     * @return  
     */
    public T crear(T entity) {
        EntityManager em = getEntityManager();
        try {
            if (em != null && entity != null) {
                em.persist(entity);
                return entity;
            }else{
            }
        } catch (Exception e) {
            System.out.println("ERROR: " + e);
        }
        return null;
    }
    
    /**
     * 
     * @param entity 
     */
    public void edit(T entity) {
        EntityManager em = getEntityManager();
        try {
            if (em != null && entity != null) {
                em.merge(entity);
            }else{
            System.out.println("algo es nulo");
            }
        } catch (Exception e) {
            System.out.println("ERROR: " + e);
        }
    }
    
    /**
     * 
     * @param entity 
     * @return  
     */
    public T editar(T entity) {
        EntityManager em = getEntityManager();
        try {
            if (em != null && entity != null) {
                em.merge(entity);
                return entity;
            }else{
            System.out.println("algo es nulo");
            }
        } catch (Exception e) {
            System.out.println("ERROR: " + e);
        }
        
        return null;
    }
    
    /**
     * 
     * @param entity 
     */
    public void remove(T entity) {
        EntityManager em = getEntityManager();
        try {
            if (em != null && entity != null) {
                em.remove(getEntityManager().merge(entity));
            }else{
            System.out.println("algo es nulo");
            }
        } catch (Exception e) {
            System.out.println("ERROR: " + e);
        }
    }
    
    /**
     * 
     * @param entity 
     * @return  
     */
    public T remover(T entity) {
        EntityManager em = getEntityManager();
        try {
            if (em != null && entity != null) {
                em.remove(getEntityManager().merge(entity));
                return entity;
            }else{
            System.out.println("algo es nulo");
            }
        } catch (Exception e) {
            System.out.println("ERROR: " + e);
        }
        return null;
    }

    /**
     * 
     * @param id
     * @return 
     */
    public T find(Object id) {
        try {
            if(id != null){
                return getEntityManager().find(entityClass, id);
            }
        } catch (Exception e) {
            System.out.println("ex: "+e);
        }
        return null;
    }

    /**
     * 
     * @return 
     */
    public List<T> findAll() {
        javax.persistence.criteria.CriteriaQuery cq = getEntityManager().getCriteriaBuilder().createQuery();
        cq.select(cq.from(entityClass));
        return getEntityManager().createQuery(cq).getResultList();
    }

    /**
     *
     * @param primero
     * @param tamanio
     * @return
     */
    public List<T> findRange(int primero, int tamanio) {
        javax.persistence.criteria.CriteriaQuery cq = getEntityManager().getCriteriaBuilder().createQuery();
        cq.select(cq.from(entityClass));
        javax.persistence.Query q = getEntityManager().createQuery(cq);
        q.setMaxResults(tamanio);
        q.setFirstResult(primero);
        return q.getResultList();
    }


    /**
     * 
     * @return 
     */
    public int count() {
        javax.persistence.criteria.CriteriaQuery cq = getEntityManager().getCriteriaBuilder().createQuery();
        javax.persistence.criteria.Root<T> rt = cq.from(entityClass);
        cq.select(getEntityManager().getCriteriaBuilder().count(rt));
        javax.persistence.Query q = getEntityManager().createQuery(cq);
        return ((Long) q.getSingleResult()).intValue();
    }
    
}
