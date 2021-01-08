package de.pinpoint.client.dataprovider;

import de.pinpoint.client.locationclient.UserInfo;
import lombok.Setter;

import java.util.Collection;

public class EventHandler {
    @Setter
    private UpdateListener listener;

    public void handleUpdate(Collection<UserInfo> list) {
        if (listener != null) {
            listener.onUpdate(list);
        }
    }
}
