package org.example.programa;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Query;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import org.example.entidades.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import java.util.List;

public class App 
{
    private EntityManager entityManager;

    public App(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public void añadirPrestamo(PrestamoEntity prestamo) {
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        entityManager.persist(prestamo);
        transaction.commit();
    }

    public LibroEntity obtenerLibro(int codigo) {
        return entityManager.find(LibroEntity.class, codigo);
    }

    public void actualizarLibro(LibroEntity libro) {
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        entityManager.merge(libro);
        transaction.commit();
    }

    public List<PrestamoEntity> obtenerPrestamosLibro(int codigoLibro) {
        Query query = entityManager.createQuery("SELECT j FROM PrestamoEntity j WHERE j.idLibro = :codigo");
        query.setParameter("codigo", codigoLibro);
        return query.getResultList();
    }

    public List<PrestamoEntity> obtenerPrestamosLibro2(int codigoLibro) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<PrestamoEntity> cq = cb.createQuery(PrestamoEntity.class);
        Root<PrestamoEntity> prestamo = cq.from(PrestamoEntity.class);
        cq.select(prestamo).where(cb.equal(prestamo.get("idLibro"), codigoLibro));

        return entityManager.createQuery(cq).getResultList();
    }

    public UsuarioEntity obtenerUsuario(int codigo) {
        return entityManager.find(UsuarioEntity.class, codigo);
    }

    public List<PrestamoEntity> obtenerPrestamosUsuario(int codigoUsuario) {
        Query query = entityManager.createQuery("SELECT j FROM PrestamoEntity j WHERE j.idUsuario = :codigo");
        query.setParameter("codigo", codigoUsuario);
        return query.getResultList();
    }

    public boolean validarExistenciaUsuario(int codigo) {
        UsuarioEntity user = entityManager.find(UsuarioEntity.class, codigo);
        return user != null;
    }

    public boolean validarExistenciaAutor(int codigo) {
        AutorEntity autor = entityManager.find(AutorEntity.class, codigo);
        return autor != null;
    }

    public boolean validarExistenciaLibro(int codigo) {
        LibroEntity libro = entityManager.find(LibroEntity.class, codigo);
        return libro != null;
    }

}
