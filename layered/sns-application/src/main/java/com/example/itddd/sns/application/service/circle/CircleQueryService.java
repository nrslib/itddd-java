package com.example.itddd.sns.application.service.circle;

import com.example.itddd.sns.application.service.circle.getcandidates.CircleGetCandidatesInputData;
import com.example.itddd.sns.application.service.circle.getcandidates.CircleGetCandidatesOutputData;

public interface CircleQueryService {
    CircleGetCandidatesOutputData getCandidates(CircleGetCandidatesInputData inputData);
}
