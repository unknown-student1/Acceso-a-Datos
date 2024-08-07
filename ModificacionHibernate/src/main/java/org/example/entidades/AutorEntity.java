package org.example.entidades;

import jakarta.persistence.*;

import java.util.Collection;

@Entity
@Table(name = "autor", schema = "biblio", catalog = "")
public class AutorEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "idAutor")
    private int idAutor;
    @Basic
    @Column(name = "NombreAutor")
    private String nombreAutor;
    @OneToMany(mappedBy = "autorByIdAutor")
    private Collection<LibroEntity> librosByIdAutor;

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

    public Collection<LibroEntity> getLibrosByIdAutor() {
        return librosByIdAutor;
    }

    public void setLibrosByIdAutor(Collection<LibroEntity> librosByIdAutor) {
        this.librosByIdAutor = librosByIdAutor;
    }
}
