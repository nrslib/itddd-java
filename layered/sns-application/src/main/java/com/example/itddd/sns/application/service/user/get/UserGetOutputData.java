package com.example.itddd.sns.application.service.user.get;

import com.example.itddd.sns.application.service.user.common.UserData;

import java.util.Optional;

public record UserGetOutputData(Optional<UserData> maybeUser) {
}
