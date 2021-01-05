package de.pinpoint.client.LocationClient;

public class RestClientFactory {
    LocationClient produceRestClient(String baseUrl) {
        return new RestClient(baseUrl);
    }
}
