package com.example.itddd.web.http.models.users.get;

import com.example.itddd.web.http.models.users.common.UserResponseModel;
import lombok.Getter;

@Getter
public class UserGetResponseModel {
    public UserResponseModel user;

    public UserGetResponseModel(UserResponseModel user) {
        this.user = user;
    }
}
