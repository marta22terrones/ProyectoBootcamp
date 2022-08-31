package com.proyecto.entities;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotEmpty;

import lombok.AllArgsConstructor;
import lombok.Builder;
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

    private boolean enable;

    // @ManyToMany(mappedBy = "ratings")
    // private List<Integer> ratings = new ArrayList<Integer>();

    public User(String username, String password, 
    String matchingPassword, String email, String avatar)  {
        super();
        this.username = username;
        this.password = password;
        this.matchingPassword = matchingPassword;
        this.email = email;
        this.avatar = avatar;
    }

}
