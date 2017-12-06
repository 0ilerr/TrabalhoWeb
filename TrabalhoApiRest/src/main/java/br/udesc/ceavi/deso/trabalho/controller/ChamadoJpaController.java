/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.udesc.ceavi.deso.trabalho.controller;

import br.udesc.ceavi.deso.trabalho.model.Chamado;
import br.udesc.ceavi.deso.trabalho.model.exceptions.NonexistentEntityException;
import br.udesc.ceavi.deso.trabalho.model.exceptions.RollbackFailureException;
import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.EntityTransaction;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.transaction.UserTransaction;

/**
 *
 * @author euler
 */
public class ChamadoJpaController implements Serializable {

    public ChamadoJpaController() {

    }

    public EntityManager getEntityManager() {
        EntityManagerFactory emf = javax.persistence.Persistence.createEntityManagerFactory("TrabalhoPU");
        return emf.createEntityManager();
    }

    public void create(Chamado chamado) throws RollbackFailureException, Exception {
        EntityManager em = null;
        em = getEntityManager();
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            em.persist(chamado);
            tx.commit();
        } catch (Exception ex) {
            try {
                tx.rollback();
            } catch (Exception re) {
                throw new RollbackFailureException("An error occurred attempting to roll back the transaction.", re);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Chamado chamado) throws NonexistentEntityException, RollbackFailureException, Exception {
        EntityManager em = null;
        em = getEntityManager();
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            chamado = em.merge(chamado);
            tx.commit();
        } catch (Exception ex) {
            try {
                tx.rollback();
            } catch (Exception re) {
                throw new RollbackFailureException("An error occurred attempting to roll back the transaction.", re);
            }
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = chamado.getId();
                if (findChamado(id) == null) {
                    throw new NonexistentEntityException("The chamado with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(Long id) throws NonexistentEntityException, RollbackFailureException, Exception {
        EntityManager em = null;
        em = getEntityManager();
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            Chamado chamado;
            try {
                chamado = em.getReference(Chamado.class, id);
                chamado.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The chamado with id " + id + " no longer exists.", enfe);
            }
            em.remove(chamado);
            tx.commit();
        } catch (Exception ex) {
            try {
                tx.rollback();
            } catch (Exception re) {
                throw new RollbackFailureException("An error occurred attempting to roll back the transaction.", re);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Chamado> findChamadoEntities() {
        return findChamadoEntities(true, -1, -1);
    }

    public List<Chamado> findChamadoEntities(int maxResults, int firstResult) {
        return findChamadoEntities(false, maxResults, firstResult);
    }

    private List<Chamado> findChamadoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Chamado.class));
            Query q = em.createQuery(cq);
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public Chamado findChamado(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Chamado.class, id);
        } finally {
            em.close();
        }
    }

    public int getChamadoCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Chamado> rt = cq.from(Chamado.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

}
