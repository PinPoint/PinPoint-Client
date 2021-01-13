package de.pinpoint.client;

import de.pinpoint.client.locationclient.LocationClient;
import de.pinpoint.client.locationclient.PinPointPosition;
import de.pinpoint.client.locationclient.RestClientFactory;
import de.pinpoint.client.locationclient.UserInfo;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.Random;
import java.util.UUID;

public class StressTest {
    String[] names = new String[]{"Bob", "Anna", "Thomas", "Niklas", "Julius", "Tobias", "Charlene", "Vanessa", "Tom", "Henry", "Phillip", "Justin", "Harry", "Potter", "Sarah", "Marie", "Dustin", "Chris", "Mara", "Rosie", "Josie", "Fiona", "Esel", "Shrek", "MemeMaster3000", "DonaldTrump", "Mia"};
    String[] colorArray = new String[]{"#f44336", "#E91E63", "#9C27B0",
            "#673AB7", "#3F51B5", "#2196F3", "#03A9F4", "#4CAF50", "#CDDC39", "#FFC107"};

    @Test
    public void postManyUserInfos() throws IOException {
        LocationClient client = new RestClientFactory().produceRestClient(TestConstants.TEST_BACKEND);
        for (int i = 0; i < 5; i++) {
            client.postInfo(getRandomUserInfo());
        }
    }

    private UserInfo getRandomUserInfo() {
        Random random = new Random();
        String name = names[random.nextInt(names.length)];
        double latitude = random.nextDouble() - 0.5 + 52.531677;
        double longitude = random.nextDouble() - 0.5 + 13.381777;
        PinPointPosition position = new PinPointPosition(longitude, latitude);
        String color = colorArray[random.nextInt(colorArray.length)];
        UserInfo user = new UserInfo(UUID.randomUUID(), name, color, position);
        return user;
    }
}
