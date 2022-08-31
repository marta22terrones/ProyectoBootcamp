package com.proyecto.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.proyecto.dao.IFilmDao;
import com.proyecto.dao.IGenreDao;
import com.proyecto.entities.Film;
import com.proyecto.entities.Genre;

@Service
public class GenreServiceImpl implements IGenreService {

    @Autowired
    private IFilmDao filmDao;

    @Autowired
    private IGenreDao genreDao;

    @Override
    public List<Film> getFilmsByGenre(int genreId) {
        Genre genre = genreDao.findById(genreId).get();
        return null;
    }

    @Override
    public List<Genre> getGenres() {
        return genreDao.findAll();
    }

    @Override
    public Genre getGenreById(int genreId) {
        return genreDao.findById(genreId).get();
    }

    @Override
    public List<Film> getFilms() {
        return filmDao.findAll();
    }
    
}