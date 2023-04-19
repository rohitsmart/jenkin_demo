package com.hospital.rohit.repository;

import com.hospital.rohit.entity.UserCredentials;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserCredentialsRepository extends JpaRepository<UserCredentials,Integer> {
    @Query("select u from UserCredentials u where u.username = ?1")
   Optional< UserCredentials> findByUsername(String username);
    @Query("select (count(u) > 0) from UserCredentials u where u.username = ?1 and u.password = ?2")
    boolean existsByUsernameAndPassword(String username, String password);


}
