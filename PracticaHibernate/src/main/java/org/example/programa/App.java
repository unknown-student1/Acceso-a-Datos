package org.example.programa;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Query;
import org.example.entidades.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import java.util.List;

public class App {
    private EntityManager entityManager;

    public App(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    // Métodos CRUD para la entidad Jugadores
    public void añadirJugador(JugadoresEntity jugador) {
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        entityManager.persist(jugador);
        transaction.commit();
    }

    public JugadoresEntity obtenerJugador(int codigo) {
        return entityManager.find(JugadoresEntity.class, codigo);
    }

    public void actualizarJugador(JugadoresEntity jugador) {
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        entityManager.merge(jugador);
        transaction.commit();
    }

    public void eliminarJugador(int codigo) {
        JugadoresEntity jugador = obtenerJugador(codigo);
        if (jugador != null) {
            EntityTransaction transaction = entityManager.getTransaction();
            transaction.begin();
            entityManager.remove(jugador);
            transaction.commit();
        }
    }

    public List<JugadoresEntity> obtenerJugadoresDeEquipo(String nombreEquipo) {
        Query query = entityManager.createQuery("SELECT j FROM JugadoresEntity j WHERE j.nombreEquipo = :nombre");
        query.setParameter("nombre", nombreEquipo);
        return query.getResultList();
    }

    public List<JugadoresEntity> obtenerTodosLosJugadores() {
        return entityManager.createQuery("SELECT j FROM JugadoresEntity j", JugadoresEntity.class).getResultList();
    }

    // Métodos CRUD para la entidad Equipos
    public void añadirEquipo(EquiposEntity equipo) {
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        entityManager.persist(equipo);
        transaction.commit();
    }

    public EquiposEntity obtenerEquipo(String nombre) {
        return entityManager.find(EquiposEntity.class, nombre);
    }

    public void actualizarEquipo(EquiposEntity equipo) {
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        entityManager.merge(equipo);
        transaction.commit();
    }

    public void actualizarNombreEquipo(String nombreActual, String nuevoNombre, String nuevaCiudad, String nuevaConferencia, String nuevaDivision) {
        // Crear una SessionFactory
        SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
        Session session = sessionFactory.openSession();
        Transaction transaction = null;

        try {
            transaction = session.beginTransaction();

            // Obtener el equipo actual
            EquiposEntity equipoActual = session.get(EquiposEntity.class, nombreActual);

            if (equipoActual != null) {
                // Crear nuevo equipo con el nuevo nombre
                EquiposEntity nuevoEquipo = new EquiposEntity();
                nuevoEquipo.setNombre(nuevoNombre);
                nuevoEquipo.setCiudad(nuevaCiudad);
                nuevoEquipo.setConferencia(nuevaConferencia);
                nuevoEquipo.setDivision(nuevaDivision);

                // Guardar el nuevo equipo
                session.save(nuevoEquipo);
                session.flush(); // Asegura que el nuevo equipo se ha guardado en la base de datos

                // Actualizar referencias en la tabla jugadores
                Query queryJugadores = session.createQuery("UPDATE JugadoresEntity j SET j.nombreEquipo = :nuevoNombre WHERE j.nombreEquipo = :nombreActual");
                queryJugadores.setParameter("nuevoNombre", nuevoNombre);
                queryJugadores.setParameter("nombreActual", nombreActual);
                queryJugadores.executeUpdate();

                // Actualizar referencias en la tabla partidos
                Query queryPartidosLocal = session.createQuery("UPDATE PartidosEntity p SET p.equipoLocal = :nuevoNombre WHERE p.equipoLocal = :nombreActual");
                queryPartidosLocal.setParameter("nuevoNombre", nuevoNombre);
                queryPartidosLocal.setParameter("nombreActual", nombreActual);
                queryPartidosLocal.executeUpdate();

                Query queryPartidosVisitante = session.createQuery("UPDATE PartidosEntity p SET p.equipoVisitante = :nuevoNombre WHERE p.equipoVisitante = :nombreActual");
                queryPartidosVisitante.setParameter("nuevoNombre", nuevoNombre);
                queryPartidosVisitante.setParameter("nombreActual", nombreActual);
                queryPartidosVisitante.executeUpdate();

                // Eliminar el equipo antiguo
                session.delete(equipoActual);

                transaction.commit();
                System.out.println("Equipo actualizado exitosamente");
            } else {
                System.out.println("Equipo no encontrado");
            }

        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
            sessionFactory.close();
        }
    }

    public void eliminarEquipo(String nombre) {
        EquiposEntity equipo = obtenerEquipo(nombre);
        if (equipo != null) {
            EntityTransaction transaction = entityManager.getTransaction();
            transaction.begin();
            entityManager.remove(equipo);
            transaction.commit();
        }
    }

    // Métodos CRUD para la entidad Estadisticas
    public void añadirEstadisticas(EstadisticasEntity estadisticas) {
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        entityManager.persist(estadisticas);
        transaction.commit();
    }

    public EstadisticasEntity obtenerEstadisticas(String temporada, int jugador) {
        return entityManager.find(EstadisticasEntity.class, new EstadisticasEntityPK(temporada, jugador));
    }

    public void actualizarEstadisticas(EstadisticasEntity estadisticas) {
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        entityManager.merge(estadisticas);
        transaction.commit();
    }

    public void eliminarEstadisticas(String temporada, int jugador) {
        EstadisticasEntity estadisticas = obtenerEstadisticas(temporada, jugador);
        if (estadisticas != null) {
            EntityTransaction transaction = entityManager.getTransaction();
            transaction.begin();
            entityManager.remove(estadisticas);
            transaction.commit();
        }
    }

    // Métodos CRUD para la entidad Partidos
    public void añadirPartido(PartidosEntity partido) {
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        entityManager.persist(partido);
        transaction.commit();
    }

    public PartidosEntity obtenerPartido(int codigo) {
        return entityManager.find(PartidosEntity.class, codigo);
    }

    public void actualizarPartido(PartidosEntity partido) {
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        entityManager.merge(partido);
        transaction.commit();
    }

    public void eliminarPartido(int codigo) {
        PartidosEntity partido = obtenerPartido(codigo);
        if (partido != null) {
            EntityTransaction transaction = entityManager.getTransaction();
            transaction.begin();
            entityManager.remove(partido);
            transaction.commit();
        }
    }

    public List<PartidosEntity> obtenerPartidosDeEquipo(String nombreEquipo) {
        Query query = entityManager.createQuery("SELECT p FROM PartidosEntity p WHERE p.equipoLocal = :nombre OR p.equipoVisitante = :nombre");
        query.setParameter("nombre", nombreEquipo);
        return query.getResultList();
    }

    public List<PartidosEntity> obtenerTodosLosPartidos() {
        return entityManager.createQuery("SELECT p FROM PartidosEntity p", PartidosEntity.class).getResultList();
    }

    public boolean validarExistenciaJugador(int codigo) {
        JugadoresEntity jugador = entityManager.find(JugadoresEntity.class, codigo);
        return jugador != null;
    }

    public boolean validarExistenciaEquipo(String nombre) {
        EquiposEntity equipo = entityManager.find(EquiposEntity.class, nombre);
        return equipo != null;
    }

    public boolean validarExistenciaEstadisticas(String temporada, int codigoJugador) {
        EstadisticasEntityPK id = new EstadisticasEntityPK(temporada, codigoJugador);
        EstadisticasEntity estadisticas = entityManager.find(EstadisticasEntity.class, id);
        return estadisticas != null;
    }

    public boolean validarExistenciaPartido(int codigo) {
        PartidosEntity partido = entityManager.find(PartidosEntity.class, codigo);
        return partido != null;
    }
}