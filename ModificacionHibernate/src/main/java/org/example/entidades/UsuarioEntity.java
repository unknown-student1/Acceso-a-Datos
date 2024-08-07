package org.example.entidades;

import jakarta.persistence.*;

import java.util.Collection;

@Entity
@Table(name = "usuario", schema = "biblio", catalog = "")
public class UsuarioEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "idUsuario")
    private int idUsuario;
    @Basic
    @Column(name = "NombreCompleto")
    private String nombreCompleto;
    @Basic
    @Column(name = "Direccion")
    private String direccion;
    @Basic
    @Column(name = "Correo")
    private String correo;
    @Basic
    @Column(name = "Telefono")
    private String telefono;
    @Basic
    @Column(name = "Foto")
    private String foto;
    @OneToMany(mappedBy = "usuarioByIdUsuario")
    private Collection<PrestamoEntity> prestamosByIdUsuario;

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getNombreCompleto() {
        return nombreCompleto;
    }

    public void setNombreCompleto(String nombreCompleto) {
        this.nombreCompleto = nombreCompleto;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    public Collection<PrestamoEntity> getPrestamosByIdUsuario() {
        return prestamosByIdUsuario;
    }

    public void setPrestamosByIdUsuario(Collection<PrestamoEntity> prestamosByIdUsuario) {
        this.prestamosByIdUsuario = prestamosByIdUsuario;
    }
}
