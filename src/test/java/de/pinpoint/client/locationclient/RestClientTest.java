package de.pinpoint.client.locationclient;

import de.pinpoint.client.TestConstants;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.Collection;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class RestClientTest {

    static UserInfo EXAMPLE_USER = new UserInfo(TestConstants.STEVE_UUID, "Steve", "#FFFFFF", PinPointPosition.NULL_ISLAND);

    @Test
    void postInfo() throws IOException {
        RestClient client = (RestClient) new RestClientFactory().produceRestClient(TestConstants.TEST_BACKEND);
        UserInfo info = EXAMPLE_USER;
        client.postInfo(info);
    }

    @Test
    void getInfoList() throws IOException {
        RestClient client = (RestClient) new RestClientFactory().produceRestClient(TestConstants.TEST_BACKEND);
        client.getInfoList(UUID.randomUUID());
    }

    @Test
    void getNewUuid() throws IOException {
        RestClient client = (RestClient) new RestClientFactory().produceRestClient(TestConstants.TEST_BACKEND);
        client.getNewUuid();
    }

    @Test
    void checkPostedInfo() throws IOException {
        RestClient client = (RestClient) new RestClientFactory().produceRestClient(TestConstants.TEST_BACKEND);
        UserInfo info = EXAMPLE_USER;
        client.postInfo(info);

        Collection<UserInfo> userList = client.getInfoList(info.getUuid());
        assertTrue(userList.contains(info));
    }

    @Test
    void getNewUuidEquals() throws IOException {
        RestClient client = (RestClient) new RestClientFactory().produceRestClient(TestConstants.TEST_BACKEND);
        UUID uuid1 = client.getNewUuid();
        UUID uuid2 = client.getNewUuid();
        assertNotEquals(uuid1, uuid2);
    }

    private UserInfo randomUserInfo(){
        return new UserInfo(UUID.randomUUID(), "Alex", "#FFFFFF", PinPointPosition.NULL_ISLAND);
    }
}