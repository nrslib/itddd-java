package com.example.itddd.web.config;

import com.example.itddd.sns.application.service.circle.CircleApplicationService;
import com.example.itddd.sns.application.service.circle.CircleQueryService;
import com.example.itddd.sns.application.service.user.UserApplicationService;
import com.example.itddd.sns.domain.models.circle.CircleFactory;
import com.example.itddd.sns.domain.models.circle.CircleRepository;
import com.example.itddd.sns.domain.models.user.UserFactory;
import com.example.itddd.sns.domain.models.user.UserRepository;
import com.example.itddd.sns.domain.services.UserService;
import com.example.itddd.sns.infrastructure.jpa.circle.CircleDataJpaRepository;
import com.example.itddd.sns.infrastructure.jpa.circle.JpaCircleQueryService;
import com.example.itddd.sns.infrastructure.jpa.circle.JpaCircleRepository;
import com.example.itddd.sns.infrastructure.jpa.user.JpaUserRepository;
import com.example.itddd.sns.infrastructure.jpa.user.UserDataJpaRepository;
import com.example.itddd.web.utility.domain.circle.InMemoryCircleFactory;
import com.example.itddd.web.utility.domain.user.InMemoryUserFactory;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EntityScan("com.example.itddd.sns.infrastructure.jpa")
@EnableJpaRepositories("com.example.itddd.sns.infrastructure.jpa")
public class DebugConfig {
    @Bean
    UserApplicationService userApplicationService(UserFactory userFactory, UserRepository userRepository, UserService userService) {
        return new UserApplicationService(userFactory, userRepository, userService);
    }

    @Bean
    UserFactory userFactory() {
        return new InMemoryUserFactory();
    }

    @Bean
    UserRepository userRepository(UserDataJpaRepository jpaRepository) {
        return new JpaUserRepository(jpaRepository);
    }

    @Bean
    UserService userService(UserRepository userRepository) {
        return new UserService(userRepository);
    }

    @Bean
    CircleApplicationService circleApplicationService(CircleFactory circleFactory, CircleRepository circleRepository, UserRepository userRepository) {
        return new CircleApplicationService(circleFactory, circleRepository, userRepository);
    }

    @Bean
    CircleFactory circleFactory() {
        return new InMemoryCircleFactory();
    }

    @Bean
    CircleRepository circleRepository(CircleDataJpaRepository jpaRepository) {
        return new JpaCircleRepository(jpaRepository);
    }

    @Bean
    CircleQueryService circleQueryService(CircleDataJpaRepository circleRepository, UserDataJpaRepository userRepository) {
        return new JpaCircleQueryService(circleRepository, userRepository);
    }
}
