package com.example.itddd.sns.application.service.user;

import com.example.itddd.common.application.exceptions.NotFoundException;
import com.example.itddd.sns.application.service.user.common.UserData;
import com.example.itddd.sns.application.service.user.get.UserGetInputData;
import com.example.itddd.sns.application.service.user.get.UserGetOutputData;
import com.example.itddd.sns.application.service.user.getlist.UserGetListOutputData;
import com.example.itddd.sns.application.service.user.register.UserRegisterInputData;
import com.example.itddd.sns.application.service.user.update.UserUpdateInputData;
import com.example.itddd.sns.domain.models.user.UserFactory;
import com.example.itddd.sns.domain.models.user.UserId;
import com.example.itddd.sns.domain.models.user.UserName;
import com.example.itddd.sns.domain.models.user.UserRepository;
import org.springframework.transaction.annotation.Transactional;

public class UserApplicationService {
    private final UserFactory userFactory;
    private final UserRepository userRepository;

    public UserApplicationService(UserFactory userFactory, UserRepository userRepository) {
        this.userFactory = userFactory;
        this.userRepository = userRepository;
    }

    public UserGetListOutputData getList() {
        var users = userRepository.findAll();
        var userIdList = users.stream().map((it) -> it.getId().value()).toList();

        return new UserGetListOutputData(userIdList);
    }

    public UserGetOutputData get(UserGetInputData inputData) {
        var userId = new UserId(inputData.id());
        var maybeUser = userRepository.find(userId);

        var maybeUserData = maybeUser.map((it) -> new UserData(it.getId().value(), it.getName().value()));

        return new UserGetOutputData(maybeUserData);
    }

    @Transactional
    public UserId register(UserRegisterInputData inputData) {
        var userName = new UserName(inputData.name());
        var user = userFactory.create(userName);
        userRepository.save(user);

        return user.getId();
    }

    @Transactional
    public void update(UserUpdateInputData inputData) {
        var userId = new UserId(inputData.id());
        var user = userRepository.find(userId)
                .orElseThrow(() -> new NotFoundException("user id: " + inputData.id()));

        if (inputData.name() != null) {
            var newUserName = new UserName(inputData.name());
            user.changeName(newUserName);
        }

        userRepository.save(user);
    }

    @Transactional
    public void delete(UserId id) {
        userRepository.delete(id);
    }
}
