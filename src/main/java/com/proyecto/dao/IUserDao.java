package com.proyecto.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.proyecto.entities.User;

@Repository
public interface IUserDao extends JpaRepository<User, Integer> {
    @Query(value = "select u from User u WHERE u.email = ?1")
    public User findByEmail(String email);
}
