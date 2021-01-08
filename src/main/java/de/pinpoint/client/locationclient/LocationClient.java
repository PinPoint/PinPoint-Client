package de.pinpoint.client.locationclient;

import java.io.IOException;
import java.util.Collection;
import java.util.UUID;

public interface LocationClient {
    /**
     * Sends / updates information like Position,
     * name and color of the marker to the server.
     */
    public void postInfo(UserInfo info) throws IOException;
    /**
     * Gets list of all users from server.
     */
    public Collection<UserInfo> getInfoList(UUID uuid) throws IOException;
    /**
     * Asks server for a new unique user id
     */
    public UUID getNewUuid() throws IOException;
}
