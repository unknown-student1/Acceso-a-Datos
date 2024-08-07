package org.example.entidades;

import java.sql.Date;

public class PrestamoEntity {
    private int idPrestamo;
    private int idUsuario;
    private int idLibro;
    private Date fecha;

    public int getIdPrestamo() {
        return idPrestamo;
    }

    public void setIdPrestamo(int idPrestamo) {
        this.idPrestamo = idPrestamo;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public int getIdLibro() {
        return idLibro;
    }

    public void setIdLibro(int idLibro) {
        this.idLibro = idLibro;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PrestamoEntity that = (PrestamoEntity) o;

        if (idPrestamo != that.idPrestamo) return false;
        if (idUsuario != that.idUsuario) return false;
        if (idLibro != that.idLibro) return false;
        if (fecha != null ? !fecha.equals(that.fecha) : that.fecha != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = idPrestamo;
        result = 31 * result + idUsuario;
        result = 31 * result + idLibro;
        result = 31 * result + (fecha != null ? fecha.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "PrestamoEntity{" +
                "idPrestamo=" + idPrestamo +
                ", idUsuario=" + idUsuario +
                ", idLibro=" + idLibro +
                ", fecha=" + fecha +
                '}';
    }
}
