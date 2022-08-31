package com.proyecto.service;

import java.util.List;

import com.proyecto.entities.Film;
import com.proyecto.entities.Genre;

public interface IGenreService {

    public List<Film> getFilmsByGenre(int genreId);
    public Genre getGenreById(int genreId);
    public List<Genre> getGenres();
    public List<Film> getFilms();
}