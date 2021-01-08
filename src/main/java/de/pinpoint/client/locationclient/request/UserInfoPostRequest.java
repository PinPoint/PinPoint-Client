package de.pinpoint.client.locationclient.request;

import de.pinpoint.client.locationclient.UserInfo;
import lombok.Getter;

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