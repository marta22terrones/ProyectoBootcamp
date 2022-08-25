package com.proyecto.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.proyecto.dao.IFilmDao;
import com.proyecto.entities.Film;
import com.proyecto.entities.Genre;

@Service
public class FilmServiceImpl implements IFilmService {

    @Autowired
    private IFilmDao filmDao;

    @Override
    public List<Film> getFilms() {
        return filmDao.findAll();
    }

    @Override
    public Film getFilm(int filmId) {
        return filmDao.findById(filmId).get();
    }

    @Override
    public void save(Film film) {
        filmDao.save(film);        
    }

    @Override
    public void delete(int filmID) {
        filmDao.deleteById(filmID);;        
    }

    @Override
    public List<Genre> getGendres(int filmId) {
        Film film = filmDao.findById(filmId).get();
        return film.getGenres();
    }
    
}
