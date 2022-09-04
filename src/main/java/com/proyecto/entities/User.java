package com.proyecto.entities;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false, length = 20)
    private String username;

    @Column(nullable = false, length = 64)
    private String password;
    
    @Column(nullable = false, length = 64)
    private String matchingPassword;

    @Column(nullable = false, unique = true, length = 45)
    private String email;
    
    private String avatar;

    // private List<Integer> rating = new ArrayList<Integer>();

   //  @ManyToMany(mappedBy = "ratings")
    // private List<Integer> ratings = new ArrayList<Integer>();


}