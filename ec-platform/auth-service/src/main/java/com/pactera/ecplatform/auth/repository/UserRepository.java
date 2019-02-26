package com.pactera.ecplatform.auth.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.pactera.ecplatform.auth.domain.Users;

@Repository("userRepository")
public interface UserRepository extends CrudRepository<Users, Long> {

  Users findByUsername(String username);
}
