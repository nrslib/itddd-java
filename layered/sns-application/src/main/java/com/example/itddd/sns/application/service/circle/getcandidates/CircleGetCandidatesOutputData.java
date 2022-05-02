package com.example.itddd.sns.application.service.circle.getcandidates;

import com.example.itddd.sns.domain.models.user.UserId;

import java.util.List;

public record CircleGetCandidatesOutputData(List<UserId> userIdList) {
}
