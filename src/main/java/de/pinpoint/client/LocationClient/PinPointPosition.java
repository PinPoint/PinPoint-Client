package de.pinpoint.client.LocationClient;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class PinPointPosition {
    private double longitude, latitude;
}
