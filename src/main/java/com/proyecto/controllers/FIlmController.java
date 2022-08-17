package com.proyecto.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.proyecto.entities.Film;
import com.proyecto.service.IFilmService;

@Controller
@RequestMapping("/")
public class FilmController {

    @Autowired
    private IFilmService filmService;

    @GetMapping("/filmList")
    public String getFilms(Model model) {

        List<Film> films = filmService.getFilms();
        model.addAttribute("filmsList", films);
        
        return "films";
    }
}
