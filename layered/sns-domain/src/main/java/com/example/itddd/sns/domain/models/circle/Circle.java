package com.example.itddd.sns.domain.models.circle;

import com.example.itddd.sns.domain.models.user.User;
import com.example.itddd.sns.domain.models.user.UserId;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Getter
public class Circle {
    private final CircleId id;
    private CircleName name;
    private UserId owner;
    private List<UserId> members;

    public Circle(CircleId id, CircleName name, UserId owner, List<UserId> members) {
        Objects.requireNonNull(id);
        Objects.requireNonNull(name);
        Objects.requireNonNull(owner);
        Objects.requireNonNull(members);

        this.id = id;
        this.name = name;
        this.owner = owner;
        this.members = members;
    }

    public void join(User member, CircleFullSpecification fullSpec) {
        Objects.requireNonNull(member);
        Objects.requireNonNull(fullSpec);
        if (fullSpec.isSatisfiedBy(this)) {
            throw new CircleFullException(id, "サークルに所属しているメンバーが上限に達しています。");
        }

        members.add(member.getId());
    }

    public int countMember() {
        if (existsOwner()) {
            return members.size() + 1;
        } else {
            return members.size();
        }
    }

    public boolean existsOwner() {
        return owner != null;
    }

    public List<UserId> getMembers() {
        return getMembers(false);
    }

    public List<UserId> getMembers(boolean containsOwner) {
        var acc = new ArrayList<UserId>();
        if (containsOwner && existsOwner()) {
            acc.add(owner);
        }
        acc.addAll(this.members);

        return acc;
    }
}
