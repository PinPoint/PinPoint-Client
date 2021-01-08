package de.pinpoint.client.locationclient;

import com.google.gson.Gson;
import de.pinpoint.client.locationclient.request.Request;
import de.pinpoint.client.locationclient.request.UserInfoPostRequest;
import de.pinpoint.client.locationclient.request.UserListRequest;
import de.pinpoint.client.locationclient.request.UuidRequest;
import de.pinpoint.client.locationclient.response.Response;
import de.pinpoint.client.locationclient.response.UserListResponse;
import de.pinpoint.client.locationclient.response.UuidResponse;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.RequestBody;

import java.io.IOException;
import java.util.Collection;
import java.util.UUID;

class RestClient implements LocationClient {
    private static final MediaType JSON
            = MediaType.get("application/json; charset=utf-8");

    private String baseUrl;
    private final Gson gson = new Gson();
    private final OkHttpClient client = new OkHttpClient();


    public RestClient(String baseUrl) {
        this.baseUrl = baseUrl;
    }

    @Override
    public void postInfo(UserInfo info) throws IOException {
        UserInfoPostRequest userInfoPostRequest = new UserInfoPostRequest(info);
        Response response = this.makeRequest(userInfoPostRequest, Response.class);
    }

    @Override
    public Collection<UserInfo> getInfoList(UUID uuid) throws IOException {
        UserListRequest userListRequest = new UserListRequest(uuid);
        UserListResponse userListResponse = this.makeRequest(userListRequest, UserListResponse.class);

        return userListResponse.getUsers();
    }

    @Override
    public UUID getNewUuid() throws IOException {
        UuidRequest uuidRequest = new UuidRequest(UUID.randomUUID());
        UuidResponse uuidResponse = this.makeRequest(uuidRequest, UuidResponse.class);

        if (uuidResponse.getStatus() != 0) {
            throw new IOException(uuidResponse.getMessage());
        }
        return uuidResponse.getUuid();
    }

    private <T extends Response> T makeRequest(Request request, Class<T> responseType) throws IOException {
        String requestJson = gson.toJson(request);
        RequestBody body = RequestBody.create(requestJson, JSON);
        okhttp3.Request httpRequest = new okhttp3.Request.Builder()
                .url(baseUrl + request.getPath())
                .post(body)
                .build();
        okhttp3.Response httpResponse = client.newCall(httpRequest).execute();
        String responseJson = httpResponse.body().string();
        return gson.fromJson(responseJson, responseType);
    }
}
