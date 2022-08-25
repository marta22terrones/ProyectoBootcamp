package com.proyecto.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.proyecto.entities.Film;
import com.proyecto.entities.Genre;
import com.proyecto.service.IFilmService;

@Controller
@RequestMapping("/")
public class FIlmController {
    
    @Autowired
    private IFilmService filmService;

    @GetMapping("/details/{id}")
    public ModelAndView details(@PathVariable(name = "id") int id){
        Film film = filmService.getFilm(id);
        List<Genre> gendres = filmService.getGendres(id);
        ModelAndView mav = new ModelAndView();
        mav.setViewName("details");
        mav.addObject("film", film);
        mav.addObject("gendres", gendres);
        return mav;
    }

    @GetMapping("/{title}")
    public ResponseEntity<List<Film>> getFilmByString(@PathVariable(name = "string") String string) {

        List<Film> films = filmService.getFilmByString(string);

        if (films != null)
            return new ResponseEntity<List<Film>>(films, HttpStatus.OK);
        else
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);

    }
}
