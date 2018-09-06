package org.pmsc.repository;

import org.pmsc.entity.User;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface UserRepository extends CrudRepository<User,Long> {

    @Query("select u from User u where u.userID=:ID and u.pwd=:pwd")
    User login(@Param("ID") String id,@Param("pwd") String pwd);

    @Transactional
    @Modifying(clearAutomatically = true)
    @Query(value="UPDATE user set pwd='000000' where userID=?1",nativeQuery = true)
    int reset(String userID);

    @Transactional
    @Modifying(clearAutomatically = true)
    @Query(value="DELETE from user where userID=?1",nativeQuery = true)
    int delete(String userID);
}
