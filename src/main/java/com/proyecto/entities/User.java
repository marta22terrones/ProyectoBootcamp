package com.proyecto.entities;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/* 
 * Además de username, password y avatar posibilidad de añadir: dateBirth, genre y rol 
 * Rol: (desplegable de roles: ADMIN O NORMAL, que podemos usar más adelante con Spring Security)
 */

 /*
  * He añadido los atributos y Getter, Setter y Constructores porque quiero ir empezando a ver el Login (Marta)
  */

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class User {
    
    private long id;
    private String username;
    private String password;
    private LocalDate dateBirth;
    private Genre genre;
    private Rol rol;
    private String avatar;

    public enum Genre {
        MAN, WOMAN, ANOTHER 
    }

    public enum Rol {
        ADMIN, NORMAL
    }
}
