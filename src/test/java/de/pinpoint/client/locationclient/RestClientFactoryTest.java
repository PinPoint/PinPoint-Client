package de.pinpoint.client.locationclient;

import de.pinpoint.client.TestConstants;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;

public class RestClientFactoryTest {
    @Test
    public void testProduceRestClient() {
        RestClientFactory factory = new RestClientFactory();
        LocationClient client = factory.produceRestClient(TestConstants.TEST_BACKEND);
        assertNotNull(client);
    }
}
