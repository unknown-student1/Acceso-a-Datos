package org.example.programa;

import jakarta.persistence.EntityManager;
import org.example.entidades.PrestamoEntity;
import org.example.entidades.UsuarioEntity;

import java.util.ArrayList;
import java.util.List;

public class App 
{
    private EntityManager entityManager;

    public App(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public UsuarioEntity obtenerUsuario(int codigo) {
        return entityManager.find(UsuarioEntity.class, codigo);
    }

    public void obtenerPrestamosUsuario(int idUsuario) {
        UsuarioEntity usuario = entityManager.find(UsuarioEntity.class, idUsuario);
        if (usuario != null) {
            //return new ArrayList<>(usuario.getPrestamosByIdUsuario());
                System.out.println("**********(Prestamos)***********");
                for (PrestamoEntity prestamoslibro : usuario.getPrestamosByIdUsuario()) {
                    System.out.println("Prestamo: " + prestamoslibro.getIdPrestamo() + " "
                            + prestamoslibro.getLibroByIdLibro().getTitulo());
                }
        }

    }
}
