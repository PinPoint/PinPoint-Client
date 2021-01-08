package de.pinpoint.client.locationclient.request;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.UUID;

@Getter
@AllArgsConstructor
public abstract class Request {
    /**
     * The uuid of the client's user the request was sent from.
     */
    private UUID userId;

    public Request(){};

    public abstract String getPath();
}