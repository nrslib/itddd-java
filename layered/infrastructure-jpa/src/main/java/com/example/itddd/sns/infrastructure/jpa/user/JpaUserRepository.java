package com.example.itddd.sns.infrastructure.jpa.user;

import com.example.itddd.sns.domain.models.user.User;
import com.example.itddd.sns.domain.models.user.UserId;
import com.example.itddd.sns.domain.models.user.UserName;
import com.example.itddd.sns.domain.models.user.UserRepository;
import org.springframework.dao.EmptyResultDataAccessException;

import java.util.List;
import java.util.Optional;

public class JpaUserRepository implements UserRepository {
    private final UserJpaRepositoryImpl repository;

    public JpaUserRepository(UserJpaRepositoryImpl jpaRepository) {
        this.repository = jpaRepository;
    }

    @Override
    public Optional<User> find(UserId id) {
        var maybeData = repository.findById(id.value());

        return maybeData.map(this::newUser);
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
                new UserName(userDataModel.getName())
        );
    }

    private UserDataModel newUserDataModel(User user) {
        return new UserDataModel(
                user.getId().value(),
                user.getName().value(),
                1
        );
    }
}