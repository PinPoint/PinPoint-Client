package de.pinpoint.client.LocationClient;

import java.util.UUID;

public class UserInfo {
    public static class PinPointPosition {
        private double latitude, longitude;

        public double getLongitude() {
            return longitude;
        }

        public void setLongitude(double longitude) {
            this.longitude = longitude;
        }

        public double getLatitude() {
            return latitude;
        }

        public void setLatitude(double latitude) {
            this.latitude = latitude;
        }
    }

    private String name, color;
    private UUID uuid;
    private PinPointPosition position;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public PinPointPosition getPosition() {
        return position;
    }

    public void setPosition(PinPointPosition position) {
        this.position = position;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public UUID getUuid() {
        return uuid;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }
}
