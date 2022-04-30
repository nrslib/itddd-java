package com.example.itddd.sns.application.service.user.common;

import com.example.itddd.sns.domain.models.user.UserType;

public record UserData(String id, String name, UserType userType) {
}
