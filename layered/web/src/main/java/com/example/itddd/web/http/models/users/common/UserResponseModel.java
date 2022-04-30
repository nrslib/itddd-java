package com.example.itddd.web.http.models.users.common;

import com.example.itddd.sns.domain.models.user.UserType;

public record UserResponseModel(String id, String name, UserType userType) {
}
