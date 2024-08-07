package TreekingApp;

import java.util.Date;

public class TrackPoint {
    private double lng;
    private double lat;
    private double ele;
    private Date time;

    public TrackPoint(double lng, double lat, double ele, Date time) {
        this.lng = lng;
        this.lat = lat;
        this.ele = ele;
        this.time = time;
    }

    public double getLng() {
        return lng;
    }

    public double getLat() {
        return lat;
    }

    public double getEle() {
        return ele;
    }

    public Date getTime() {
        return time;
    }

    // Método para calcular la distancia en metros entre dos TrackPoint utilizando la fórmula de Haversine
    public double distanceTo(TrackPoint other) {
        double earthRadius = 6371000; // Radio de la Tierra en metros

        // Convertir las latitudes y longitudes de grados a radianes
        double lat1Rad = Math.toRadians(this.lat);
        double lon1Rad = Math.toRadians(this.lng);
        double lat2Rad = Math.toRadians(other.lat);
        double lon2Rad = Math.toRadians(other.lng);

        // Calcular la diferencia de latitud y longitud
        double deltaLat = lat2Rad - lat1Rad;
        double deltaLon = lon2Rad - lon1Rad;

        // Calcular la distancia utilizando la fórmula de Haversine
        double a = Math.sin(deltaLat / 2) * Math.sin(deltaLat / 2) +
                Math.cos(lat1Rad) * Math.cos(lat2Rad) *
                        Math.sin(deltaLon / 2) * Math.sin(deltaLon / 2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));

        return earthRadius * c;
    }
}
