package org.pmsc.repository;

import org.pmsc.entity.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UserRepository extends CrudRepository<User,Long> {
    List<User> findAll();

    @Query("select u from User u where u.userID=:ID and u.pwd=:pwd")
    User login(@Param("ID") String id,@Param("pwd") String pwd);

    User save(User u);
}
