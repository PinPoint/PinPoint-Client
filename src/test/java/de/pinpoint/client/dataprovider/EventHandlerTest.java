package de.pinpoint.client.dataprovider;

import de.pinpoint.client.locationclient.UserInfo;
import org.junit.jupiter.api.Test;

import java.util.Collection;
import java.util.Collections;

import static org.mockito.Mockito.*;

public class EventHandlerTest {
    @Test
    public void testregisterListenr(){
        EventHandler handler = new EventHandler();
        handler.registerListener(mock(UpdateListener.class));
    }


    @Test
    public void testHandleUpdateNoListener(){
        EventHandler handler = new EventHandler();
        handler.handleUpdate(Collections.emptyList());
    }

    @Test
    public void testHandleUpdate(){
        EventHandler handler = new EventHandler();
        Collection<UserInfo> dummyList = mock(Collection.class);
        UpdateListener dummyListener = mock(UpdateListener.class);
        handler.registerListener(dummyListener);
        handler.handleUpdate(dummyList);

        // verify onUpdate() was called once
        verify(dummyListener, times(1)).onUpdate(dummyList);
    }

    @Test
    public void testHandleUpdateMultipleListener(){
        EventHandler handler = new EventHandler();
        Collection<UserInfo> dummyList = mock(Collection.class);
        UpdateListener dummyListener1 = mock(UpdateListener.class);
        UpdateListener dummyListener2 = mock(UpdateListener.class);
        handler.registerListener(dummyListener1);
        handler.registerListener(dummyListener2);
        handler.handleUpdate(dummyList);

        // verify all onUpdate() were called
        verify(dummyListener1, times(1)).onUpdate(dummyList);
        verify(dummyListener2, times(1)).onUpdate(dummyList);
    }
}
