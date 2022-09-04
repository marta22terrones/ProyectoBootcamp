package com.proyecto.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Rate implements Serializable {

    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    private int id;
    private Integer rate;

    @ManyToMany(mappedBy = "genres")
  //  @NotEmpty(message = "You must have at least one film in this genre")
    private List<Film> rateFilm = new ArrayList<Film>();
}
