package com.proyecto.DTO;

import com.proyecto.entities.Rol;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class UserRegDTO {
    private int id;
    private String username;
    private String password;
    private String matchingPassword;
    private String email;  
    private String avatar;
    private Rol rol;

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
