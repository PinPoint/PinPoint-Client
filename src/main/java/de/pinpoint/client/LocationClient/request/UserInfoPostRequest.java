package de.pinpoint.client.LocationClient.request;

import de.pinpoint.client.LocationClient.UserInfo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;

import java.util.UUID;

@Getter
public class UserInfoPostRequest extends Request {
    private UserInfo userInfo;

    public UserInfoPostRequest(UserInfo info) {
        super(info.getUuid());
        this.userInfo = info;
    }

    @Override
    public String getPath() {
        return "update";
    }
}