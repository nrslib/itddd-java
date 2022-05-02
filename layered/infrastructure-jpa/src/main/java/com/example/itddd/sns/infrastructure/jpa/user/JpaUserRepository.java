package com.example.itddd.sns.infrastructure.jpa.user;

import com.example.itddd.sns.domain.models.user.*;

import java.util.List;
import java.util.Optional;

public class JpaUserRepository implements UserRepository {
    private final UserDataJpaRepository repository;

    public JpaUserRepository(UserDataJpaRepository jpaRepository) {
        this.repository = jpaRepository;
    }

    @Override
    public Optional<User> find(UserId id) {
        var maybeData = repository.findById(id.value());

        return maybeData.map(this::newUser);
    }

    @Override
    public Optional<User> find(UserName name) {
        return Optional.empty();
    }

    @Override
    public List<User> find(List<UserId> idList) {
        return null;
    }

    @Override
    public List<User> findAll() {
        return repository
                .findAll()
                .stream()
                .map(this::newUser)
                .toList();
    }

    @Override
    public void save(User user) {
        var userDataModel = newUserDataModel(user);

        repository.save(userDataModel);
    }

    @Override
    public void delete(UserId id) {
        if (repository.existsById(id.value())) {
            repository.deleteById(id.value());
        }
    }

    private User newUser(UserDataModel userDataModel) {
        return new User(
                new UserId(userDataModel.getId()),
                new UserName(userDataModel.getName()),
                convertUserType(userDataModel.getType())
        );
    }

    private UserDataModel newUserDataModel(User user) {
        return new UserDataModel(
                user.getId().value(),
                user.getName().value(),
                convert(user.getType())
        );
    }

    private int convert(UserType type) {
        return switch (type) {
            case Normal -> 1;
            case Premium -> 2;
        };
    }

    private UserType convertUserType(int typeInt) {
        return switch (typeInt) {
            case 1 -> UserType.Normal;
            case 2 -> UserType.Premium;
            default -> throw new IllegalStateException("Unexpected value: " + typeInt);
        };
    }
}