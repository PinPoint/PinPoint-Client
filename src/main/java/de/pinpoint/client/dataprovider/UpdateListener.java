package de.pinpoint.client.dataprovider;

import de.pinpoint.client.locationclient.UserInfo;

import java.util.Collection;

/**
 * UpdateListener is called when the data provider fetched new information from the server;
 */
public interface UpdateListener {
    public void onUpdate(Collection<UserInfo> list);
}
