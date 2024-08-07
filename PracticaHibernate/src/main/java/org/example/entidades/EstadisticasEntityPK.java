package org.example.entidades;

import java.io.Serializable;

public class EstadisticasEntityPK implements Serializable {
    private String temporada;
    private int jugador;

    public EstadisticasEntityPK() {}

    public EstadisticasEntityPK(String temporada, int jugador) {
        this.temporada = temporada;
        this.jugador = jugador;
    }

    public String getTemporada() {
        return temporada;
    }

    public void setTemporada(String temporada) {
        this.temporada = temporada;
    }

    public int getJugador() {
        return jugador;
    }

    public void setJugador(int jugador) {
        this.jugador = jugador;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        EstadisticasEntityPK that = (EstadisticasEntityPK) o;

        if (jugador != that.jugador) return false;
        if (temporada != null ? !temporada.equals(that.temporada) : that.temporada != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = temporada != null ? temporada.hashCode() : 0;
        result = 31 * result + jugador;
        return result;
    }
}
