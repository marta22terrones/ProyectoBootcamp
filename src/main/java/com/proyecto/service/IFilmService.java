package com.proyecto.service;

import java.util.List;

import com.proyecto.entities.Film;

public interface IFilmService {
    public List<Film> getFilms();
    public Film getFilm(int filmId);
    public void save(Film film);
    public void delete(int filmId);
}
