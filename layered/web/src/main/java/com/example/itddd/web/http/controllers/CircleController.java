package com.example.itddd.web.http.controllers;

import com.example.itddd.sns.application.service.circle.CircleApplicationService;
import com.example.itddd.sns.application.service.circle.CircleQueryService;
import com.example.itddd.sns.application.service.circle.create.CircleCreateInputData;
import com.example.itddd.sns.application.service.circle.get.CircleGetInputData;
import com.example.itddd.sns.application.service.circle.getcandidates.CircleGetCandidatesInputData;
import com.example.itddd.sns.application.service.circle.join.CircleJoinInputData;
import com.example.itddd.sns.domain.models.user.UserId;
import com.example.itddd.web.http.models.circles.common.CircleResponseModel;
import com.example.itddd.web.http.models.circles.get.CircleGetResponseModel;
import com.example.itddd.web.http.models.circles.getcandidates.CircleGetCandidatesRequestModel;
import com.example.itddd.web.http.models.circles.getcandidates.CircleGetCandidatesResponseModel;
import com.example.itddd.web.http.models.circles.getlist.CircleGetListResponseModel;
import com.example.itddd.web.http.models.circles.post.CirclePostRequestModel;
import com.example.itddd.web.http.models.circles.post.CirclePostResponseModel;
import com.example.itddd.web.http.models.circles.join.CircleJoinRequestModel;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/circles")
public class CircleController {
    private final CircleApplicationService circleApplicationService;
    private final CircleQueryService circleQueryService;

    public CircleController(CircleApplicationService circleApplicationService, CircleQueryService circleQueryService) {
        this.circleApplicationService = circleApplicationService;
        this.circleQueryService = circleQueryService;
    }

    @GetMapping
    public CircleGetListResponseModel getList() {
        var outputData = circleApplicationService.getList();
        var circleResponses = outputData
                .circles()
                .stream()
                .map(it -> new CircleResponseModel(it.id(), it.name(), it.ownerId(), it.memberIdList()))
                .toList();

        return new CircleGetListResponseModel(circleResponses);
    }

    @GetMapping("/{id}")
    public CircleGetResponseModel get(@PathVariable("id") String id) {
        var inputData = new CircleGetInputData(id);
        var outputData = circleApplicationService.get(inputData);

        var circleResponse = outputData.maybeCircle()
                .map(it -> new CircleResponseModel(it.id(), it.name(), it.ownerId(), it.memberIdList()))
                .orElse(null);

        return new CircleGetResponseModel(circleResponse);
    }

    @PostMapping
    public CirclePostResponseModel post(@RequestBody CirclePostRequestModel body) {
        var inputData = new CircleCreateInputData(body.circleName(), body.ownerId());
        var circle = circleApplicationService.create(inputData);

        return new CirclePostResponseModel(
                new CircleResponseModel(
                        circle.id(),
                        circle.name(),
                        circle.ownerId(),
                        circle.memberIdList()
                )
        );
    }

    @PutMapping("/{id}/join")
    public void join(@PathVariable("id") String circleId, @RequestBody CircleJoinRequestModel body) {
        var inputData = new CircleJoinInputData(circleId, body.memberId());
        circleApplicationService.join(inputData);
    }

    @GetMapping("/{id}/candidates")
    public CircleGetCandidatesResponseModel getCandidates(@PathVariable("id") String id, @RequestParam int page, @RequestParam int size) {
        var inputData = new CircleGetCandidatesInputData(id, page, size);
        var outputData = circleQueryService.getCandidates(inputData);

        var userIdList = outputData.userIdList().stream().map(UserId::value).toList();

        return new CircleGetCandidatesResponseModel(userIdList);
    }
}
