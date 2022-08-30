package com.proyecto.entities;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

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

    @NotEmpty(message = "The username cannot be empty")
    // @Size(min = 4, max = 20, message = "The username must have between 4 and 20 characters")
    private String username;

    @NotEmpty(message = "The password cannot be empty")
    // @Size(min = 4, max = 20, message = "The password must have between 4 and 20 characters")
    private String password;
    private String matchingPassword;

 
    private String email;
    
    private String avatar;

   //  @ManyToMany(mappedBy = "ratings")
    // private List<Integer> ratings = new ArrayList<Integer>();


}
