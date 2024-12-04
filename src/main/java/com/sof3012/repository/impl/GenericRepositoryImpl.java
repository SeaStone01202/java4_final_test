package com.sof3012.repository.impl;

import com.sof3012.utils.HibernateUtil;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;

import java.util.List;

public class GenericRepositoryImpl<T, E> {

    private final EntityManager em;

    private final Class<T> entityClass;

    public GenericRepositoryImpl(Class<T> entityClass) {
        this.em = HibernateUtil.getEntityManager();
        this.entityClass = entityClass;
    }

    public T create(T entity) {
        try {
            em.getTransaction().begin();
            em.persist(entity);
            em.getTransaction().commit();
            System.out.println("Created successfully");
            return entity;
        } catch (Exception e) {
            em.getTransaction().rollback();
            System.out.println("Cannot created" + entity.getClass().getName() + " to Database");
            throw new RuntimeException(e);
        }
    }

    public T update(T entity) {
        try {
            em.getTransaction().begin();
            em.merge(entity);
            em.getTransaction().commit();
            System.out.println("Updated successfully");
            return entity;
        } catch (Exception e) {
            em.getTransaction().rollback();
            System.out.println("Cannot updated" + entity.getClass().getName() + " to Database");
            throw new RuntimeException(e);
        }
    }

    public T delete(T entity) {
        try {
            em.getTransaction().begin();
            em.remove(entity);
            em.getTransaction().commit();
            System.out.println("Deleted successfully");
            return entity;
        } catch (Exception e) {
            em.getTransaction().rollback();
            System.out.println("Cannot deleted" + entity.getClass().getName() + " to Database");
            throw new RuntimeException(e);
        }
    }

    public T findById(E idEntity) {
        return em.find(entityClass, idEntity);
    }

    public List<T> findAll(boolean existIsActive) {
        StringBuilder sql = new StringBuilder();
        sql.append("SELECT e FROM ").append(entityClass.getSimpleName()).append(" e");
        if (existIsActive) {
            sql.append(" WHERE e.active = true");
        }
        TypedQuery<T> query = em.createQuery(sql.toString(), entityClass);
        return query.getResultList();
    }

    public int countSize() {
        String sql = "SELECT COUNT(*) FROM "+entityClass.getSimpleName();
        Query query = em.createQuery(sql);
        return ((Long) query.getSingleResult()).intValue();
    }

    public List<T> findAll(boolean existIsActive, int pageNumber, int pageSize) {
        StringBuilder sql = new StringBuilder();
        sql.append("SELECT e FROM ").append(entityClass.getSimpleName()).append(" e");
        if (existIsActive) {
            sql.append(" WHERE e.active = true");
        }
        TypedQuery<T> query = em.createQuery(sql.toString(), entityClass);
        query.setFirstResult((pageNumber - 1) * pageSize);
        query.setMaxResults(pageSize);
        return query.getResultList();
    }

    public T findOne(String sql, Object... params) {
        TypedQuery<T> query = em.createQuery(sql, entityClass);
        for (int i = 0; i < params.length; i++) {
            query.setParameter(i + 1, params[i]);
        }
        List<T> resultList = query.getResultList();
        if (resultList.isEmpty()) {
            return null;
        }
        return resultList.get(0);
    }

    public List<T> findMany(String sql, Object... params) {
        TypedQuery<T> query = em.createQuery(sql, entityClass);
        for (int i = 0; i < params.length; i++) {
            query.setParameter(i + 1, params[i]);
        }
        return query.getResultList();
    }

    @SuppressWarnings("unchecked")
    public List<Object[]> findManyByNativeQury(String sql, Object... params) {
        Query query = em.createNativeQuery(sql, entityClass);
        for (int i = 0; i < params.length; i++) {
            query.setParameter(i + 1, params[i]);
        }
        return query.getResultList();
    }
}
