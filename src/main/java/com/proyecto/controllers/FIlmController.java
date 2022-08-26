package com.proyecto.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.proyecto.entities.Film;
import com.proyecto.entities.Genre;
import com.proyecto.service.IFilmService;

@Controller
@RequestMapping("/")
public class FIlmController {

    @Autowired
    private IFilmService filmService;

    @GetMapping("/catalogue")
    public String getFilms(Model model) {
    
        List<Film> films = filmService.getFilms();        
        model.addAttribute("films", films);
        return "filmsList";
    }
}
