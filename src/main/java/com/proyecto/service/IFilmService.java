package com.proyecto.service;

import java.util.List;

import com.proyecto.entities.Actor;
import com.proyecto.entities.Director;
import com.proyecto.entities.Film;
import com.proyecto.entities.Genre;
import com.proyecto.entities.Rate;

public interface IFilmService {
    public List<Film> getFilms();
    public Film getFilm(int filmId);
    public List<Genre> getGendres(int filmId);
    public List<Genre> getGenres();
    public List<Rate> getRates();
    public List<Rate> getRates(int filmId);
    public double getAverageRate (int filmId);
    public List<Actor> getActors(int filmId);
    public List<Director> getDirectors(int filmId);
    public void save(Film film);
    public void delete(int filmId);
    public List<Film> getFilmByString(String string);
    public void saveRate(int filmId, Rate rate);
    public List<Film> getBestRated();
}
