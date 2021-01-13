package de.pinpoint.client.dataprovider;

import de.pinpoint.client.TestConstants;
import de.pinpoint.client.locationclient.LocationClient;
import de.pinpoint.client.locationclient.RestClientFactory;
import de.pinpoint.client.locationclient.UserInfo;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;


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
    void testInvokeUpdate() throws Throwable {
        UUID uuid = UUID.randomUUID();

        // prepare mocks
        LocationClient clientMock = mock(LocationClient.class);
        DataProvider provider = new DataProvider(clientMock, uuid);

        provider.invokeUpdate();

        // Verify our LocationClient was used to update Users
        verify(clientMock, times(1)).getInfoList(uuid);
    }

    @Test
    void testUpdateListener() throws Throwable {
        UUID uuid = UUID.randomUUID();
        Collection<UserInfo> dummyList = Collections.emptyList();

        // prepare mocks
        LocationClient clientMock = mock(LocationClient.class);
        when(clientMock.getInfoList(uuid)).thenReturn(dummyList);
        UpdateListener listenerMock = mock(UpdateListener.class);

        DataProvider provider = new DataProvider(clientMock, uuid);
        provider.addUpdateListener(listenerMock);
        provider.invokeUpdate();

        // Verify our UpdateListener was called
        verify(listenerMock, times(1)).onUpdate(Mockito.any());
    }

    @Test
    void testGetUsers() throws Throwable {
        UUID uuid = UUID.randomUUID();
        Collection<UserInfo> dummyList = new ArrayList<>();
        UserInfo dummyUser = mock(UserInfo.class);
        dummyList.add(dummyUser);

        // prepare mocks
        LocationClient clientMock = mock(LocationClient.class);
        when(clientMock.getInfoList(uuid)).thenReturn(dummyList);

        DataProvider provider = new DataProvider(clientMock, uuid);
        provider.invokeUpdate();

        // Verify our dummy list is returned by getUsers()
        assertEquals(dummyList.size(), provider.getUsers().size());
        assertEquals(getAny(dummyList), getAny(provider.getUsers()));
    }

    @Test
    void testGetUserCached() throws Throwable {
        UUID uuid = UUID.randomUUID();
        Collection<UserInfo> dummyList = new ArrayList<>();
        UserInfo dummyUser = mock(UserInfo.class);
        dummyList.add(dummyUser);
        assertFalse(dummyList.isEmpty());

        // prepare mocks
        LocationClient clientMock = mock(LocationClient.class);
        when(clientMock.getInfoList(uuid)).thenReturn(dummyList);

        DataProvider provider = new DataProvider(clientMock, uuid);
        provider.invokeUpdate();

        for (int i = 0; i < 5; i++) {
            Collection<UserInfo> returnedList = provider.getUsers();
            assertEquals(dummyUser, getAny(returnedList));
        }

        // verify if cache worked and it requested client only once
        verify(clientMock, times(1)).getInfoList(uuid);
    }

    @Test
    void testNoUpdateListener() throws Throwable {
        UUID uuid = UUID.randomUUID();
        Collection<UserInfo> dummyList = new ArrayList<>();
        UserInfo dummyUser = mock(UserInfo.class);
        dummyList.add(dummyUser);

        // prepare mock
        LocationClient clientMock = mock(LocationClient.class);

        DataProvider provider = new DataProvider(clientMock, uuid);
        provider.getUsers();
    }

    @Test
    void testClearCache() throws Throwable {
        UUID uuid = UUID.randomUUID();
        Collection<UserInfo> dummyList = new ArrayList<>();
        UserInfo dummyUser = mock(UserInfo.class);
        dummyList.add(dummyUser);

        // prepare mocks
        LocationClient clientMock = mock(LocationClient.class);
        when(clientMock.getInfoList(uuid)).thenReturn(dummyList);

        DataProvider provider = new DataProvider(clientMock, uuid);
        provider.invokeUpdate();

        provider.clearCache();

        // Verify cache is empty
        assertTrue(provider.getUsers().isEmpty());
    }

    private <T> T getAny(Collection<T> collection) {
        if (collection.isEmpty())
            throw new IllegalArgumentException("given collection is empty");
        if (collection instanceof List) {
            return ((List<T>) collection).get(0);
        } else {
            return collection.stream().findAny().get();
        }
    }
}