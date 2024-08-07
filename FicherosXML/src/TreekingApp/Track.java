package TreekingApp;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Track {
    private String name;
    private Date date;
    private List<TrackPoint> trackPoints;

    public Track(String name, Date date) {
        this.name = name;
        this.date = date;
        this.trackPoints = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public Date getDate() {
        return date;
    }

    public List<TrackPoint> getTrackPoints() {
        return trackPoints;
    }

    public void addTrackPoint(TrackPoint trackPoint) {
        trackPoints.add(trackPoint);
    }

    public double getMaxElevation() {
        double maxElevation = Double.MIN_VALUE;
        for (TrackPoint trackPoint : trackPoints) {
            maxElevation = Math.max(maxElevation, trackPoint.getEle());
        }
        return maxElevation;
    }

    public double getMinElevation() {
        double minElevation = Double.MAX_VALUE;
        for (TrackPoint trackPoint : trackPoints) {
            minElevation = Math.min(minElevation, trackPoint.getEle());
        }
        return minElevation;
    }
}
