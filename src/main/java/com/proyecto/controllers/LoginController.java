package com.proyecto.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.proyecto.entities.Genre;
import com.proyecto.service.IFilmService;

@Controller
// @RequestMapping("/")
public class LoginController {
    
    @Autowired
    private IFilmService filmService;

    @GetMapping("/login")
    public String login(ModelMap map) {
        List<Genre> genres = filmService.getGenres();
        map.addAttribute("genres", genres);
        return "login";
    }
}
