package com.example.itddd.sns.application.service.circle;

import com.example.itddd.common.application.exceptions.NotFoundException;
import com.example.itddd.sns.application.service.circle.common.CircleData;
import com.example.itddd.sns.application.service.circle.create.CircleCreateInputData;
import com.example.itddd.sns.application.service.circle.get.CircleGetInputData;
import com.example.itddd.sns.application.service.circle.get.CircleGetOutputData;
import com.example.itddd.sns.application.service.circle.getlist.CircleGetListOutputData;
import com.example.itddd.sns.application.service.circle.join.CircleJoinInputData;
import com.example.itddd.sns.domain.models.circle.*;
import com.example.itddd.sns.domain.models.user.UserId;
import com.example.itddd.sns.domain.models.user.UserRepository;
import org.springframework.transaction.annotation.Transactional;

public class CircleApplicationService {
    private final CircleFactory circleFactory;
    private final CircleRepository circleRepository;
    private final UserRepository userRepository;

    public CircleApplicationService(CircleFactory circleFactory, CircleRepository circleRepository, UserRepository userRepository) {
        this.circleFactory = circleFactory;
        this.circleRepository = circleRepository;
        this.userRepository = userRepository;
    }

    public CircleGetListOutputData getList() {
        var circles = circleRepository.findAll()
                .stream()
                .map(CircleData::new)
                .toList();

        return new CircleGetListOutputData(circles);
    }

    public CircleGetOutputData get(CircleGetInputData inputData) {
        var id = new CircleId(inputData.id());
        var maybeCircle = circleRepository.find(id);

        var circleData = maybeCircle
                .map(CircleData::new);

        return new CircleGetOutputData(circleData);
    }

    @Transactional
    public CircleData create(CircleCreateInputData inputData) {
        var ownerId = new UserId(inputData.ownerId());
        var owner = userRepository.find(ownerId)
                .orElseThrow(() -> new NotFoundException("サークルのオーナーとなるユーザが見つかりませんでした。(userId:" + ownerId.value() + ")"));

        var circleName = new CircleName(inputData.name());
        var circle = circleFactory.create(circleName, owner);
        circleRepository.save(circle);

        return new CircleData(circle);
    }

    @Transactional
    public void join(CircleJoinInputData inputData) {
        var memberId = new UserId(inputData.memberId());
        var member = userRepository.find(memberId)
                .orElseThrow(() -> new NotFoundException("サークルのオーナーとなるユーザが見つかりませんでした。(userId:" + memberId.value() + ")"));

        var circleId = new CircleId(inputData.circleId());
        var circle = circleRepository.find(circleId)
                .orElseThrow(() -> new NotFoundException("サークルが見つかりませんでした。(circleId:" + circleId.value() + ")"));

        var fullSpec = new CircleFullSpecification(userRepository);
        circle.join(member, fullSpec);

        circleRepository.save(circle);
    }
}
