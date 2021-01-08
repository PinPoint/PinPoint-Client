package de.pinpoint.client.locationclient;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class PinPointPosition {
    private double longitude, latitude;
}
