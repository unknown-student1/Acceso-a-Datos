package org.example.entidades;

public class AutorEntity {
    private int idAutor;
    private String nombreAutor;

    public int getIdAutor() {
        return idAutor;
    }

    public void setIdAutor(int idAutor) {
        this.idAutor = idAutor;
    }

    public String getNombreAutor() {
        return nombreAutor;
    }

    public void setNombreAutor(String nombreAutor) {
        this.nombreAutor = nombreAutor;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        AutorEntity that = (AutorEntity) o;

        if (idAutor != that.idAutor) return false;
        if (nombreAutor != null ? !nombreAutor.equals(that.nombreAutor) : that.nombreAutor != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = idAutor;
        result = 31 * result + (nombreAutor != null ? nombreAutor.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "AutorEntity{" +
                "idAutor=" + idAutor +
                ", nombreAutor='" + nombreAutor + '\'' +
                '}';
    }
}
