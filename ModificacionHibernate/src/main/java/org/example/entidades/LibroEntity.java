package org.example.entidades;

import jakarta.persistence.*;

import java.util.Collection;

@Entity
@Table(name = "libro", schema = "biblio", catalog = "")
public class LibroEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "idLibro")
    private int idLibro;
    @Basic
    @Column(name = "ISBN")
    private String isbn;
    @Basic
    @Column(name = "Titulo")
    private String titulo;
    @Basic
    @Column(name = "NumeroEjemplares")
    private byte numeroEjemplares;
    @ManyToOne
    @JoinColumn(name = "idAutor", referencedColumnName = "idAutor", nullable = false)
    private AutorEntity autorByIdAutor;
    @OneToMany(mappedBy = "libroByIdLibro")
    private Collection<PrestamoEntity> prestamosByIdLibro;

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

    public AutorEntity getAutorByIdAutor() {
        return autorByIdAutor;
    }

    public void setAutorByIdAutor(AutorEntity autorByIdAutor) {
        this.autorByIdAutor = autorByIdAutor;
    }

    public Collection<PrestamoEntity> getPrestamosByIdLibro() {
        return prestamosByIdLibro;
    }

    public void setPrestamosByIdLibro(Collection<PrestamoEntity> prestamosByIdLibro) {
        this.prestamosByIdLibro = prestamosByIdLibro;
    }
}
