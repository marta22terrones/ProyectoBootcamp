package com.proyecto.service;

import java.util.List;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.proyecto.entities.User;

/*
 * Prototipo IUserService: opción de aumentar métodos
 */
public interface IUserService extends UserDetailsService {
    
    public User getUser(int id);
    public List<User> getUsers();
    public void saveUser (User user);
    public void deleteUser (int id);

}
