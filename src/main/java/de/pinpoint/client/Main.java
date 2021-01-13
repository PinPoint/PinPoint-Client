package de.pinpoint.client;

import de.pinpoint.client.locationclient.LocationClient;
import de.pinpoint.client.locationclient.PinPointPosition;
import de.pinpoint.client.locationclient.RestClientFactory;
import de.pinpoint.client.locationclient.UserInfo;

import java.io.IOException;
import java.util.Collection;
import java.util.UUID;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello world!");
        RestClientFactory restClientFactory = new RestClientFactory();
        LocationClient client = restClientFactory.produceRestClient("https://thedst.de/pinpoint/api/v1/");
        try {
            UUID uuid = client.getNewUuid();
            UserInfo info = new UserInfo(uuid, "Steve", "#FFFFFF", new PinPointPosition(0.0, 0.0));
            client.postInfo(info);
            Collection<UserInfo> list = client.getInfoList(uuid);

            UUID uuid2 = client.getNewUuid();
            UserInfo info2 = new UserInfo(uuid2, "Alex", "#FFFFFF", new PinPointPosition(0.6, 5.0));
            client.postInfo(info2);
            Collection<UserInfo> list2 = client.getInfoList(uuid2);
            System.out.println(list2.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
