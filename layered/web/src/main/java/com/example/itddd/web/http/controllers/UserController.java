package com.example.itddd.web.http.controllers;

import com.example.itddd.sns.application.service.user.UserApplicationService;
import com.example.itddd.sns.application.service.user.get.UserGetInputData;
import com.example.itddd.sns.application.service.user.register.UserRegisterInputData;
import com.example.itddd.sns.application.service.user.update.UserUpdateInputData;
import com.example.itddd.sns.domain.models.user.UserId;
import com.example.itddd.web.http.models.users.common.UserResponseModel;
import com.example.itddd.web.http.models.users.get.UserGetResponseModel;
import com.example.itddd.web.http.models.users.getList.UserGetListResponseModel;
import com.example.itddd.web.http.models.users.post.UserPostRequestModel;
import com.example.itddd.web.http.models.users.post.UserPostResponseModel;
import com.example.itddd.web.http.models.users.put.UserPutRequestModel;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;

@RestController
@RequestMapping("api/users")
public class UserController {
    private final UserApplicationService userApplicationService;

    public UserController(UserApplicationService userApplicationService) {
        this.userApplicationService = userApplicationService;
    }

    @GetMapping
    public UserGetListResponseModel get() {
        var outputData = userApplicationService.getList();

        return new UserGetListResponseModel(outputData.idList());
    }

    @GetMapping("/{id}")
    public UserGetResponseModel get(@PathVariable("id") String id) {
        Objects.requireNonNull(id);

        var inputData = new UserGetInputData(id);
        var outputData = userApplicationService.get(inputData);

        var maybeUser = outputData.maybeUser().map((it) ->
                new UserResponseModel(it.id(), it.name())
        );

        return new UserGetResponseModel(maybeUser.orElse(null));
    }

    @PostMapping
    public UserPostResponseModel post(@RequestBody UserPostRequestModel body) {
        var inputData = new UserRegisterInputData(body.name());
        var id = userApplicationService.register(inputData);

        return new UserPostResponseModel(
                new UserResponseModel(
                        id.value(),
                        inputData.name()
                )
        );
    }

    @PutMapping("/{id}")
    public void put(@PathVariable("id") String id, @RequestBody UserPutRequestModel body) {
        var inputData = new UserUpdateInputData(id, body.name());
        userApplicationService.update(inputData);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") String id) {
        var userId = new UserId(id);
        userApplicationService.delete(userId);
    }
}
