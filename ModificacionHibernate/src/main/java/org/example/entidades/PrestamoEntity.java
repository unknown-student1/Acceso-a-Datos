package org.example.entidades;

import jakarta.persistence.*;

import java.sql.Date;

@Entity
@Table(name = "prestamo", schema = "biblio", catalog = "")
public class PrestamoEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "idPrestamo")
    private int idPrestamo;
    @Basic
    @Column(name = "fecha")
    private Date fecha;
    @ManyToOne
    @JoinColumn(name = "idUsuario", referencedColumnName = "idUsuario", nullable = false)
    private UsuarioEntity usuarioByIdUsuario;
    @ManyToOne
    @JoinColumn(name = "idLibro", referencedColumnName = "idLibro", nullable = false)
    private LibroEntity libroByIdLibro;

    public int getIdPrestamo() {
        return idPrestamo;
    }

    public void setIdPrestamo(int idPrestamo) {
        this.idPrestamo = idPrestamo;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public UsuarioEntity getUsuarioByIdUsuario() {
        return usuarioByIdUsuario;
    }

    public void setUsuarioByIdUsuario(UsuarioEntity usuarioByIdUsuario) {
        this.usuarioByIdUsuario = usuarioByIdUsuario;
    }

    public LibroEntity getLibroByIdLibro() {
        return libroByIdLibro;
    }

    public void setLibroByIdLibro(LibroEntity libroByIdLibro) {
        this.libroByIdLibro = libroByIdLibro;
    }

    @Override
    public String toString() {
        return "PrestamoEntity{" +
                "idPrestamo=" + idPrestamo +
                ", fecha=" + fecha +
                ", usuarioByIdUsuario=" + usuarioByIdUsuario +
                ", libroByIdLibro=" + libroByIdLibro +
                '}';
    }
}
