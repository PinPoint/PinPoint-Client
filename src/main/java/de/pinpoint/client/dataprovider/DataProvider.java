package de.pinpoint.client.dataprovider;

import de.pinpoint.client.locationclient.LocationClient;
import de.pinpoint.client.locationclient.UserInfo;
import lombok.RequiredArgsConstructor;

import java.io.IOException;
import java.util.Collection;
import java.util.Collections;
import java.util.UUID;

@RequiredArgsConstructor
public class DataProvider {
    private final LocationClient client;
    private final UUID userId;
    private EventHandler eventHandler = new EventHandler();
    private Collection<UserInfo> userCache;

    public void invokeUpdate() throws IOException {
        Collection<UserInfo> newList = this.client.getInfoList(userId);
        this.userCache = Collections.unmodifiableCollection(newList);
        this.eventHandler.handleUpdate(this.userCache);
    }

    public void addUpdateListener(UpdateListener listener){
        this.eventHandler.registerListener(listener);
    }

    public Collection<UserInfo> getUsers() {
        if(userCache == null){
            return Collections.emptyList();
        }
        return userCache;
    }
}
