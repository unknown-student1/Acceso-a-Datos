package org.example.entidades;

public class LibroEntity {
    private int idLibro;
    private String isbn;
    private String titulo;
    private byte numeroEjemplares;
    private int idAutor;

    public int getIdLibro() {
        return idLibro;
    }

    public void setIdLibro(int idLibro) {
        this.idLibro = idLibro;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public byte getNumeroEjemplares() {
        return numeroEjemplares;
    }

    public void setNumeroEjemplares(byte numeroEjemplares) {
        this.numeroEjemplares = numeroEjemplares;
    }

    public int getIdAutor() {
        return idAutor;
    }

    public void setIdAutor(int idAutor) {
        this.idAutor = idAutor;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        LibroEntity that = (LibroEntity) o;

        if (idLibro != that.idLibro) return false;
        if (numeroEjemplares != that.numeroEjemplares) return false;
        if (idAutor != that.idAutor) return false;
        if (isbn != null ? !isbn.equals(that.isbn) : that.isbn != null) return false;
        if (titulo != null ? !titulo.equals(that.titulo) : that.titulo != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = idLibro;
        result = 31 * result + (isbn != null ? isbn.hashCode() : 0);
        result = 31 * result + (titulo != null ? titulo.hashCode() : 0);
        result = 31 * result + (int) numeroEjemplares;
        result = 31 * result + idAutor;
        return result;
    }

    @Override
    public String toString() {
        return "LibroEntity{" +
                "idLibro=" + idLibro +
                ", isbn='" + isbn + '\'' +
                ", titulo='" + titulo + '\'' +
                ", numeroEjemplares=" + numeroEjemplares +
                ", idAutor=" + idAutor +
                '}';
    }
}
