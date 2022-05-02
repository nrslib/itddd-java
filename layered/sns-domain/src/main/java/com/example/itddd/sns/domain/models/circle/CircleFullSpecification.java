package com.example.itddd.sns.domain.models.circle;

import com.example.itddd.sns.domain.models.user.User;
import com.example.itddd.sns.domain.models.user.UserRepository;

import java.util.List;

public class CircleFullSpecification {
    private final UserRepository userRepository;

    public CircleFullSpecification(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public boolean isSatisfiedBy(Circle circle) {
        var memberCount = circle.countMember();
        if (memberCount < 30) {
            return false;
        }

        var memberIdList = circle.getMembers();
        var members = userRepository.find(memberIdList);
        var premiumMemberCount = countPremium(members);
        var max = premiumMemberCount > 10 ? 50 : 30;

        return memberCount >= max;
    }

    private long countPremium(List<User> members) {
        return members.stream().filter(User::isPremium).count();
    }
}
