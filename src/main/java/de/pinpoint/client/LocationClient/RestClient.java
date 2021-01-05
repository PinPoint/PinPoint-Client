package de.pinpoint.client.LocationClient;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import okhttp3.*;

import java.io.IOException;
import java.util.ArrayList;
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
        String json = gson.toJson(info);
        RequestBody body = RequestBody.create(json, JSON);
        Request request = new Request.Builder()
                .url(baseUrl + "/update")
                .post(body)
                .build();

        client.newCall(request).execute();
    }

    @Override
    public Collection<UserInfo> getInfoList() throws IOException {
        Request request = new Request.Builder()
                .url(baseUrl + "/list")
                .build();
        Response response = client.newCall(request).execute();

        return gson.fromJson(response.body().string(), new TypeToken<ArrayList<UserInfo>>(){}.getType());
    }

    @Override
    public UUID getNewUuid() throws IOException {
        Request request = new Request.Builder()
                .url(baseUrl + "/generate")
                .build();
        Response response = client.newCall(request).execute();
        return UUID.fromString(response.body().string());
    }
}
