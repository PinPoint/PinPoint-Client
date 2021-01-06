package de.pinpoint.client.LocationClient;

public class RestClientFactory {
    public LocationClient produceRestClient(String baseUrl) {
        return new RestClient(baseUrl);
    }
}
