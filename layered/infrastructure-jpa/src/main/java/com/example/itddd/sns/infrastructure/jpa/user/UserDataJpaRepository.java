package com.example.itddd.sns.infrastructure.jpa.user;

import com.example.itddd.sns.domain.models.user.User;
import com.example.itddd.sns.domain.models.user.UserId;
import com.example.itddd.sns.domain.models.user.UserName;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UserDataJpaRepository extends JpaRepository<UserDataModel, String> {
    @Query(value = "SELECT user FROM UserDataModel user WHERE user.name = ?#{name.value()}")
    UserDataModel find(@Param("name") UserName name);

    @Query(value = "SELECT user FROM UserDataModel user WHERE user.id IN (:idList)")
    List<UserDataModel> find(@Param("idList") List<UserId> idList);

    @Query(value = "SELECT user FROM UserDataModel user WHERE user.id NOT IN (:exceptIdList)")
    List<UserDataModel> findWithExceptIds(@Param("exceptIdList")List<String> exceptIdList, Pageable pageable);
}
