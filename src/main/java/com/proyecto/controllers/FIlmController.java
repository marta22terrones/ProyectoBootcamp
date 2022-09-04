package com.proyecto.controllers;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.proyecto.entities.Actor;
import com.proyecto.entities.Director;
import com.proyecto.entities.Film;
import com.proyecto.entities.Genre;
import com.proyecto.entities.Rate;
import com.proyecto.service.IFilmService;
import com.proyecto.service.IGenreService;

@Controller
@RequestMapping("/")
public class FIlmController {
    
    @Autowired
    private IFilmService filmService;

    @Autowired
    private IGenreService genreService;

    @GetMapping("/home")
    public ModelAndView showHome() {
        List<Genre> genres = filmService.getGenres();
        ModelAndView mav = new ModelAndView();
        mav.addObject("genres", genres);
        return mav;
    }

    @GetMapping("/rate/{id}")
    public ModelAndView formRateFilm(@PathVariable(name="id") int id){
        ModelAndView mav = new ModelAndView();
        List<Genre> genres = filmService.getGenres();
        List<Rate> rates = filmService.getRates();
        mav.addObject("genres", genres);
        mav.setViewName("rate");
        mav.addObject("rates", rates);
        return mav;
    }

    @GetMapping("/catalogue")
    public String getFilms(Model model) {
    
        List<Genre> genres = filmService.getGenres();
        List<Film> films = filmService.getFilms();        
        model.addAttribute("films", films);
        model.addAttribute("genres", genres);
        return "filmsList";
    }

    // * MAL ACTION FORM : /addRate/film.id : VER ENTRE HTML Y FILM CONTROLLER
    @PostMapping("/addRate/{id}")
    public ModelAndView addRate(@PathVariable(name="id") int id, @PathVariable(name="rate") Rate rate) {
        Film film = filmService.getFilm(id);
        filmService.saveRate(id, rate);
        List<Genre> gendres = filmService.getGendres(id);
        List<Actor> actors = filmService.getActors(id);
        List<Director> directors = filmService.getDirectors(id);
        List<Genre> genres = filmService.getGenres();
        List<Rate> rates = filmService.getRates();
        ModelAndView mav = new ModelAndView();
        mav.setViewName("rate");
        mav.addObject("film", film);
        mav.addObject("gendres", gendres);
        mav.addObject("actors", actors);
        mav.addObject("directors", directors);
        mav.addObject("genres", genres);
        mav.addObject("rates", rates);
        return mav;
    }

    @GetMapping("/catalogue/{id}")
    public ModelAndView details(@PathVariable(name = "id") int id){
        Film film = filmService.getFilm(id);
        List<Genre> gendres = filmService.getGendres(id);
        List<Actor> actors = filmService.getActors(id);
        List<Director> directors = filmService.getDirectors(id);
        List<Genre> genres = filmService.getGenres();
        int average = filmService.getAverageRate(id);

        filmService.getAverageRate(id);
        ModelAndView mav = new ModelAndView();
        mav.setViewName("details");
        mav.addObject("film", film);
        mav.addObject("gendres", gendres);
        mav.addObject("actors", actors);
        mav.addObject("directors", directors);
        mav.addObject("genres", genres);
        mav.addObject("average", average);
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


    @GetMapping("/genre/{id}")

    public ModelAndView filterByGenre(@PathVariable(name = "id") int id){

        ModelAndView mav = new ModelAndView("filmsList.html");
        List<Genre> genres = filmService.getGenres();

        int genreId = id;
        List<Film> films = genreService.getFilms();
        List<Film> filteredFilms = new ArrayList<>();

        for (Film film : films) {
            for (Genre genre : film.getGenres()) {
                if(genre.getId() == genreId){
                    filteredFilms.add(film);
                }
            }
        }
        mav.addObject("films", filteredFilms);
        mav.addObject("genres", genres);
        return mav;
    }

}
