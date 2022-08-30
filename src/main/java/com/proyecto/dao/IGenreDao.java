package com.proyecto.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.proyecto.entities.Genre;

@Repository
public interface IGenreDao extends JpaRepository <Genre, Integer>{
    
}
