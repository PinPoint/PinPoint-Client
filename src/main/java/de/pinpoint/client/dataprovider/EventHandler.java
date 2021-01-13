package de.pinpoint.client.dataprovider;

import de.pinpoint.client.locationclient.UserInfo;

import java.util.ArrayList;
import java.util.Collection;

public class EventHandler {

    private Collection<UpdateListener> listeners = new ArrayList<>();

    public void handleUpdate(Collection<UserInfo> list) {
        synchronized (listeners) {
            this.listeners.forEach(u -> u.onUpdate(list));
        }
    }

    public void registerListener(UpdateListener listener) {
        synchronized (listeners) {
            this.listeners.add(listener);
        }
    }
}
