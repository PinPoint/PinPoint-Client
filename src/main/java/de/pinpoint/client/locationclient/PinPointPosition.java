package de.pinpoint.client.locationclient;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class PinPointPosition {
    public static PinPointPosition NULL_ISLAND = new PinPointPosition(0.0,0.0);
    private double longitude, latitude;
}
