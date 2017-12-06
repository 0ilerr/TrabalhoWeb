/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.udesc.ceavi.deso.trabalho.controller;

import br.udesc.ceavi.deso.trabalho.model.Venda;
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
public class VendaJpaController implements Serializable {

    public VendaJpaController() {

    }

    public EntityManager getEntityManager() {
        EntityManagerFactory emf = javax.persistence.Persistence.createEntityManagerFactory("TrabalhoPU");
        return emf.createEntityManager();
    }

    public void create(Venda venda) throws RollbackFailureException, Exception {
        EntityManager em = null;
        em = getEntityManager();
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            em.persist(venda);
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

    public void edit(Venda venda) throws NonexistentEntityException, RollbackFailureException, Exception {
        EntityManager em = null;
        em = getEntityManager();
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            venda = em.merge(venda);
            tx.commit();
        } catch (Exception ex) {
            try {
                tx.rollback();
            } catch (Exception re) {
                throw new RollbackFailureException("An error occurred attempting to roll back the transaction.", re);
            }
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = venda.getId();
                if (findVenda(id) == null) {
                    throw new NonexistentEntityException("The venda with id " + id + " no longer exists.");
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
            Venda venda;
            try {
                venda = em.getReference(Venda.class, id);
                venda.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The venda with id " + id + " no longer exists.", enfe);
            }
            em.remove(venda);
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

    public List<Venda> findVendaEntities() {
        return findVendaEntities(true, -1, -1);
    }

    public List<Venda> findVendaEntities(int maxResults, int firstResult) {
        return findVendaEntities(false, maxResults, firstResult);
    }

    private List<Venda> findVendaEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Venda.class));
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

    public Venda findVenda(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Venda.class, id);
        } finally {
            em.close();
        }
    }

    public int getVendaCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Venda> rt = cq.from(Venda.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

}
