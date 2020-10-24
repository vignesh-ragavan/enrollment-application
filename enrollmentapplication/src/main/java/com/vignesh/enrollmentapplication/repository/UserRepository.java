package com.vignesh.enrollmentapplication.repository;

import com.vignesh.enrollmentapplication.model.User;
import org.springframework.data.domain.Example;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Long> {

     Optional<User> findByUserName(String username);

    @Query("SELECT u.name from User u WHERE u.id in (:pIdList)")
    List<String> findUserNames(@Param("pIdList") List<Long> idList);

}
