package com.example.itddd.sns.application.service.circle.get;

import com.example.itddd.sns.application.service.circle.common.CircleData;

import java.util.Optional;

public record CircleGetOutputData(Optional<CircleData> maybeCircle) {
}
