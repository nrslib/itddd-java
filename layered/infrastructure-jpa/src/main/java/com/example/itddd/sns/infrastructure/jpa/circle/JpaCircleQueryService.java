package com.example.itddd.sns.infrastructure.jpa.circle;

import com.example.itddd.common.application.exceptions.NotFoundException;
import com.example.itddd.sns.application.service.circle.CircleQueryService;
import com.example.itddd.sns.application.service.circle.getcandidates.CircleGetCandidatesInputData;
import com.example.itddd.sns.application.service.circle.getcandidates.CircleGetCandidatesOutputData;
import com.example.itddd.sns.domain.models.user.User;
import com.example.itddd.sns.domain.models.user.UserId;
import com.example.itddd.sns.infrastructure.jpa.user.UserDataJpaRepository;
import com.example.itddd.sns.infrastructure.jpa.user.UserDataModel;
import org.springframework.data.domain.PageRequest;

import java.util.ArrayList;

public class JpaCircleQueryService implements CircleQueryService {
    private final CircleDataJpaRepository circleRepository;
    private final UserDataJpaRepository userRepository;

    public JpaCircleQueryService(CircleDataJpaRepository circleRepository, UserDataJpaRepository userRepository) {
        this.circleRepository = circleRepository;
        this.userRepository = userRepository;
    }

    @Override
    public CircleGetCandidatesOutputData getCandidates(CircleGetCandidatesInputData inputData) {
        var circle = circleRepository.findById(inputData.circleId())
                .orElseThrow(NotFoundException::new);

        var exceptTargets = new ArrayList<String>();
        exceptTargets.add(circle.getOwnerId());
        exceptTargets.addAll(circle.getMembers());

        var userLists = userRepository
                .findWithExceptIds(exceptTargets, PageRequest.of(inputData.page(), inputData.size()));

        return new CircleGetCandidatesOutputData(userLists.stream().map(it -> new UserId(it.getId())).toList());
    }
}
