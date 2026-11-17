package com.tp.jpa.repository;

import com.tp.jpa.model.Base;
import com.tp.jpa.util.JPAUtil;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;

import java.util.List;
import java.util.Optional;

public abstract class BaseRepository<T extends Base> {

    private final Class<T> entityClass;
    private final EntityManagerFactory emf;

    public BaseRepository(Class<T> entityClass) {
        this.entityClass = entityClass;
        this.emf = JPAUtil.getEntityManagerFactory();
    }

    protected Class<T> getEntityClass() {
        return entityClass;
    }

    // Guarda una entidad nueva o actualiza una existente.
    public T guardar(T entity) {

        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();

        try {
            tx.begin();

            T resultado;

            if (entity.getId() == null) {
                em.persist(entity);
                resultado = entity;
            } else {
                resultado = em.merge(entity);
            }

            tx.commit();

            return resultado;

        } catch (RuntimeException e) {

            if (tx.isActive()) {
                tx.rollback();
            }

            throw e;

        } finally {
            em.close();
        }
    }

    // Busca una entidad por su ID.
    public Optional<T> buscarPorId(Long id) {

        EntityManager em = emf.createEntityManager();

        try {
            T entity = em.find(entityClass, id);

            return Optional.ofNullable(entity);

        } finally {
            em.close();
        }
    }

    // Lista solamente las entidades que no fueron dadas de baja.
    public List<T> listarActivos() {

        EntityManager em = emf.createEntityManager();

        try {

            String nombreEntidad = entityClass.getSimpleName();

            String jpql = "SELECT e FROM " + nombreEntidad
                    + " e WHERE e.eliminado = false";

            return em.createQuery(jpql, entityClass)
                    .getResultList();

        } finally {
            em.close();
        }
    }

    // Realiza una baja lógica.
    public boolean eliminarLogico(Long id) {

        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();

        try {

            T entity = em.find(entityClass, id);

            if (entity == null || entity.isEliminado()) {
                return false;
            }

            tx.begin();

            entity.setEliminado(true);

            em.merge(entity);

            tx.commit();

            return true;

        } catch (RuntimeException e) {

            if (tx.isActive()) {
                tx.rollback();
            }

            throw e;

        } finally {
            em.close();
        }
    }
}