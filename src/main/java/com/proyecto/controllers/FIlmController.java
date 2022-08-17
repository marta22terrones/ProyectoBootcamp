package com.proyecto.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.proyecto.entities.Film;
import com.proyecto.service.IFilmService;

@Controller
@RequestMapping("/")
public class FilmController {
    
    @Autowired
    private IFilmService filmService;

    @GetMapping("/details({id}")
    public ModelAndView details(@PathVariable(name = "id") int id){
        Film film = filmService.getFilm(id);
        ModelAndView mav = new ModelAndView();
        mav.setViewName("details");
        mav.addObject("film", film);
        return mav;
    } 
}
