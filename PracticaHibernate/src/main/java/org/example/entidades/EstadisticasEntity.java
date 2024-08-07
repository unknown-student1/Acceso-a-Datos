package org.example.entidades;

public class EstadisticasEntity {
    private String temporada;
    private int jugador;
    private Double puntosPorPartido;
    private Double asistenciasPorPartido;
    private Double taponesPorPartido;
    private Double rebotesPorPartido;

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

    public Double getPuntosPorPartido() {
        return puntosPorPartido;
    }

    public void setPuntosPorPartido(Double puntosPorPartido) {
        this.puntosPorPartido = puntosPorPartido;
    }

    public Double getAsistenciasPorPartido() {
        return asistenciasPorPartido;
    }

    public void setAsistenciasPorPartido(Double asistenciasPorPartido) {
        this.asistenciasPorPartido = asistenciasPorPartido;
    }

    public Double getTaponesPorPartido() {
        return taponesPorPartido;
    }

    public void setTaponesPorPartido(Double taponesPorPartido) {
        this.taponesPorPartido = taponesPorPartido;
    }

    public Double getRebotesPorPartido() {
        return rebotesPorPartido;
    }

    public void setRebotesPorPartido(Double rebotesPorPartido) {
        this.rebotesPorPartido = rebotesPorPartido;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        EstadisticasEntity that = (EstadisticasEntity) o;

        if (jugador != that.jugador) return false;
        if (temporada != null ? !temporada.equals(that.temporada) : that.temporada != null) return false;
        if (puntosPorPartido != null ? !puntosPorPartido.equals(that.puntosPorPartido) : that.puntosPorPartido != null)
            return false;
        if (asistenciasPorPartido != null ? !asistenciasPorPartido.equals(that.asistenciasPorPartido) : that.asistenciasPorPartido != null)
            return false;
        if (taponesPorPartido != null ? !taponesPorPartido.equals(that.taponesPorPartido) : that.taponesPorPartido != null)
            return false;
        if (rebotesPorPartido != null ? !rebotesPorPartido.equals(that.rebotesPorPartido) : that.rebotesPorPartido != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = temporada != null ? temporada.hashCode() : 0;
        result = 31 * result + jugador;
        result = 31 * result + (puntosPorPartido != null ? puntosPorPartido.hashCode() : 0);
        result = 31 * result + (asistenciasPorPartido != null ? asistenciasPorPartido.hashCode() : 0);
        result = 31 * result + (taponesPorPartido != null ? taponesPorPartido.hashCode() : 0);
        result = 31 * result + (rebotesPorPartido != null ? rebotesPorPartido.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "EstadisticasEntity{" +
                "temporada='" + temporada + '\'' +
                ", jugador=" + jugador +
                ", puntosPorPartido=" + puntosPorPartido +
                ", asistenciasPorPartido=" + asistenciasPorPartido +
                ", taponesPorPartido=" + taponesPorPartido +
                ", rebotesPorPartido=" + rebotesPorPartido +
                '}';
    }
}
