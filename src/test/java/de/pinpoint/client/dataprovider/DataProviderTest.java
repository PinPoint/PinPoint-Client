package de.pinpoint.client.dataprovider;

import de.pinpoint.client.TestConstants;
import de.pinpoint.client.locationclient.LocationClient;
import de.pinpoint.client.locationclient.RestClientFactory;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class DataProviderTest {

    @Test
    void getEmptyUsers() {
        String testBackend = TestConstants.TEST_BACKEND;
        RestClientFactory factory = new RestClientFactory();
        LocationClient client = factory.produceRestClient(testBackend);
        UUID uuid = UUID.randomUUID();
        DataProvider provider = new DataProvider(client, uuid);

        assertTrue(provider.getUsers().isEmpty());
    }

    @Test
    void invokeUpdate() throws Throwable {
        String testBackend = TestConstants.TEST_BACKEND;
        RestClientFactory factory = new RestClientFactory();
        LocationClient client = factory.produceRestClient(testBackend);
        UUID uuid = UUID.randomUUID();
        DataProvider provider = new DataProvider(client, uuid);

        provider.invokeUpdate();
    }
}