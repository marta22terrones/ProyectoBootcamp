package com.proyecto.DTO;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

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
public class UserRegDTO {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String username;
    private String password;
    private String matchingPassword;
    private String email;  
    private String avatar;


    public UserRegDTO(String username, String password, 
            String matchingPassword, String email, String avatar)  {
                super();
                this.username = username;
                this.password = password;
                this.matchingPassword = matchingPassword;
                this.email = email;
                this.avatar = avatar;
            }

}
