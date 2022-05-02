package com.example.itddd.sns.infrastructure.jpa.circle;

import com.example.itddd.sns.domain.models.circle.Circle;
import com.example.itddd.sns.domain.models.circle.CircleId;
import com.example.itddd.sns.domain.models.circle.CircleName;
import com.example.itddd.sns.domain.models.circle.CircleRepository;
import com.example.itddd.sns.domain.models.user.UserId;

import java.util.List;
import java.util.Optional;

public class JpaCircleRepository implements CircleRepository {
    private final CircleDataJpaRepository repository;

    public JpaCircleRepository(CircleDataJpaRepository repository) {
        this.repository = repository;
    }

    @Override
    public Optional<Circle> find(CircleId id) {
        var maybeCircleData = repository.findById(id.value());

        return maybeCircleData.map(this::newCircle);
    }

    @Override
    public List<Circle> findAll() {
        return repository.findAll()
                .stream()
                .map(this::newCircle)
                .toList();
    }

    @Override
    public void save(Circle circle) {
        var dataModel = newCircleDataModel(circle);
        repository.save(dataModel);
    }

    @Override
    public void delete(CircleId id) {
        if(repository.existsById(id.value())) {
            repository.deleteById(id.value());
        }
    }

    private CircleDataModel newCircleDataModel(Circle circle) {
        return new CircleDataModel(
                circle.getId().value(),
                circle.getName().value(),
                circle.getOwner().value(),
                circle.getMembers().stream().map(UserId::value).toList()
        );
    }

    private Circle newCircle(CircleDataModel circleDataModel) {
        return new Circle(
               new CircleId(circleDataModel.getId()),
               new CircleName(circleDataModel.getName()),
               new UserId(circleDataModel.getOwnerId()),
               circleDataModel.getMembers().stream().map(UserId::new).toList()
        );
    }
}
