package com.example.firstWebApp.repository;

import com.example.firstWebApp.entities.user;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface userRepository extends JpaRepository<user,Long> {
   @Query("select a from user a where a.UserName= :UserName and a.password= :password")
    Optional<user> login(@Param(value = "UserName") String UserName, @Param(value = "password") String password);

    @Repository
    public interface UserRepository extends JpaRepository<user, Long> {
        Optional<user> findUserById(Long id);
        // Additional query methods if needed
    }

}

