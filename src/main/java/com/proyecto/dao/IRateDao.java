package com.proyecto.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.proyecto.entities.Rate;

@Repository
public interface IRateDao extends JpaRepository <Rate, Integer>{
    
}
