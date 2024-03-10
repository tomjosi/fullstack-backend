package com.springboot.fullstackBackend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.springboot.fullstackBackend.model.User;


@Repository
public interface UserRepository extends JpaRepository<User, Long>{

}
