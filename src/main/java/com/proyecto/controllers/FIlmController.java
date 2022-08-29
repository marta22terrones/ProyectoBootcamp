package com.proyecto.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.proyecto.entities.Actor;
import com.proyecto.entities.Director;
import com.proyecto.entities.Film;
import com.proyecto.entities.Genre;
import com.proyecto.service.IFilmService;

@Controller
@RequestMapping("/")
public class FIlmController {
    
    @Autowired
    private IFilmService filmService;

    @GetMapping("/catalogue/{id}")
    public ModelAndView details(@PathVariable(name = "id") int id){
        Film film = filmService.getFilm(id);
        List<Genre> gendres = filmService.getGendres(id);
        List<Actor> actors = filmService.getActors(id);
        List<Director> directors = filmService.getDirectors(id);
        ModelAndView mav = new ModelAndView();
        mav.setViewName("details");
        mav.addObject("film", film);
        mav.addObject("gendres", gendres);
        mav.addObject("actors", actors);
        mav.addObject("directors", directors);
        return mav;
    }

    @PostMapping("/catalogue/{title}")
    public ModelAndView searchByString(@PathVariable(name = "title") String title){
        List<Film> films = filmService.getFilmByString(title);
        ModelAndView mav = new ModelAndView();
        mav.setViewName("{title}");
        mav.addObject("film", films);
        return mav;
    }

}
