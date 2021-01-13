package de.pinpoint.client.locationclient;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class PinPointPosition {
    public static PinPointPosition NULL_ISLAND = new PinPointPosition(0.0, 0.0);
    private double longitude, latitude;

    /**
     * Calculates the distance between 2 PinpointPositions.
     * Source: https://stackoverflow.com/questions/837872/calculate-distance-in-meters-when-you-know-longitude-and-latitude-in-java
     *
     * @return distance in meters
     */
    public double distance(PinPointPosition other) {
        double earthRadius = 6371000; //meters
        double dLat = Math.toRadians(other.getLatitude() - this.latitude);
        double dLng = Math.toRadians(other.longitude - this.longitude);
        double a = Math.sin(dLat / 2) * Math.sin(dLat / 2) +
                Math.cos(Math.toRadians(this.latitude)) * Math.cos(Math.toRadians(other.longitude)) *
                        Math.sin(dLng / 2) * Math.sin(dLng / 2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        double dist = earthRadius * c;
        return dist;
    }
}
