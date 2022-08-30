package com.proyecto.controllers;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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

    @GetMapping("/catalogue")
    public String getFilms(Model model) {
    
        List<Genre> genres = filmService.getGenres();
        List<Film> films = filmService.getFilms();        
        model.addAttribute("films", films);
        model.addAttribute("genres", genres);
        return "filmsList";
    }

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

    @GetMapping("/catalogueSort")
    public String getFilmsSort(Model model) {
    
        List<Genre> genres = filmService.getGenres();
        Comparator<Film> comparador = Comparator.comparing(Film::getTitle);
        List<Film> films = filmService.getFilms().stream().sorted(comparador).collect(Collectors.toList());        
        model.addAttribute("films", films);
        model.addAttribute("genres", genres);
        return "filmsList";
    }

    // @GetMapping("/catalogueSort")
    // public String getFilmsNotSort(Model model) {
    
    //     Comparator<Film> comparador = !Comparator.comparing(Film::getTitle);
    //     List<Film> films = filmService.getFilms().stream().sorted(comparador).collect(Collectors.toList());        
    //     model.addAttribute("films", films);
    //     return "filmsList";
    // }

}
