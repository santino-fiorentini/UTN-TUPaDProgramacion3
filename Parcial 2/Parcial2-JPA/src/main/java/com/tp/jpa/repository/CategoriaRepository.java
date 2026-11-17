package com.tp.jpa.repository;

import com.tp.jpa.model.Categoria;
import com.tp.jpa.model.Producto;
import com.tp.jpa.util.JPAUtil;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;

import java.util.List;

public class CategoriaRepository extends BaseRepository<Categoria> {

    public CategoriaRepository() {
        super(Categoria.class);
    }

    // Busca los productos activos pertenecientes a una categoría.
    public List<Producto> buscarProductosPorCategoria(Long categoriaId) {

        EntityManager em = JPAUtil.getEntityManagerFactory()
                .createEntityManager();

        try {

            // Se recorre la colección productos de Categoria mediante JOIN.
            // Se filtra por el ID de categoría y por productos no eliminados.
            String jpql = "SELECT p FROM Categoria c JOIN c.productos p "
                    + "WHERE c.id = :catId AND p.eliminado = false";

            return em.createQuery(jpql, Producto.class)
                    .setParameter("catId", categoriaId)
                    .getResultList();

        } finally {
            em.close();
        }
    }

    // Persiste un producto y lo asocia a una categoría.
    public Producto agregarProducto(Long categoriaId, Producto producto) {

        EntityManager em = JPAUtil.getEntityManagerFactory()
                .createEntityManager();

        EntityTransaction tx = em.getTransaction();

        try {

            tx.begin();

            Categoria categoria = em.find(Categoria.class, categoriaId);

            if (categoria == null || categoria.isEliminado()) {
                throw new IllegalArgumentException(
                        "La categoría no existe o está dada de baja."
                );
            }

            // Primero se persiste el producto.
            em.persist(producto);

            // Después se asocia a la categoría.
            categoria.addProducto(producto);

            tx.commit();

            return producto;

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