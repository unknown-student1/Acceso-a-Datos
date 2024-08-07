package org.example.entidades;

public class PartidosEntity {
    private int codigo;
    private String equipoLocal;
    private String equipoVisitante;
    private Integer puntosLocal;
    private Integer puntosVisitante;
    private String temporada;

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getEquipoLocal() {
        return equipoLocal;
    }

    public void setEquipoLocal(String equipoLocal) {
        this.equipoLocal = equipoLocal;
    }

    public String getEquipoVisitante() {
        return equipoVisitante;
    }

    public void setEquipoVisitante(String equipoVisitante) {
        this.equipoVisitante = equipoVisitante;
    }

    public Integer getPuntosLocal() {
        return puntosLocal;
    }

    public void setPuntosLocal(Integer puntosLocal) {
        this.puntosLocal = puntosLocal;
    }

    public Integer getPuntosVisitante() {
        return puntosVisitante;
    }

    public void setPuntosVisitante(Integer puntosVisitante) {
        this.puntosVisitante = puntosVisitante;
    }

    public String getTemporada() {
        return temporada;
    }

    public void setTemporada(String temporada) {
        this.temporada = temporada;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PartidosEntity that = (PartidosEntity) o;

        if (codigo != that.codigo) return false;
        if (equipoLocal != null ? !equipoLocal.equals(that.equipoLocal) : that.equipoLocal != null) return false;
        if (equipoVisitante != null ? !equipoVisitante.equals(that.equipoVisitante) : that.equipoVisitante != null)
            return false;
        if (puntosLocal != null ? !puntosLocal.equals(that.puntosLocal) : that.puntosLocal != null) return false;
        if (puntosVisitante != null ? !puntosVisitante.equals(that.puntosVisitante) : that.puntosVisitante != null)
            return false;
        if (temporada != null ? !temporada.equals(that.temporada) : that.temporada != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = codigo;
        result = 31 * result + (equipoLocal != null ? equipoLocal.hashCode() : 0);
        result = 31 * result + (equipoVisitante != null ? equipoVisitante.hashCode() : 0);
        result = 31 * result + (puntosLocal != null ? puntosLocal.hashCode() : 0);
        result = 31 * result + (puntosVisitante != null ? puntosVisitante.hashCode() : 0);
        result = 31 * result + (temporada != null ? temporada.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Partidos{" +
                "codigo=" + codigo +
                ", equipoLocal='" + equipoLocal + '\'' +
                ", equipoVisitante='" + equipoVisitante + '\'' +
                ", puntosLocal=" + puntosLocal +
                ", puntosVisitante=" + puntosVisitante +
                ", temporada='" + temporada + '\'' +
                '}';
    }
}
