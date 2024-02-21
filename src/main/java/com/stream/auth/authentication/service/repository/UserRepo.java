package com.stream.auth.authentication.service.repository;


import com.stream.auth.authentication.service.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface UserRepo extends JpaRepository<User,Integer> {

    Optional<User> findByEmail(String email);
}
