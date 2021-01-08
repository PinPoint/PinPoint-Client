package de.pinpoint.client.locationclient;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

import java.util.UUID;

@ToString
@Getter
@AllArgsConstructor
public class UserInfo {
    private UUID uuid;
    private String name;
    private String color;
    private PinPointPosition position;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserInfo userInfo = (UserInfo) o;
        return uuid.equals(userInfo.uuid);
    }

    @Override
    public int hashCode() {
        return uuid.hashCode();
    }
}
