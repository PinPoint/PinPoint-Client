package de.pinpoint.client.locationclient;

public class RestClientFactory {
    public LocationClient produceRestClient(String baseUrl) {
        return new RestClient(baseUrl);
    }
}
