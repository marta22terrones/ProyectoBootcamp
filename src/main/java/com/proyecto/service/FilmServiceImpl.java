package com.proyecto.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.proyecto.dao.IFilmDao;
import com.proyecto.dao.IGenreDao;
import com.proyecto.dao.IRateDao;
import com.proyecto.entities.Actor;
import com.proyecto.entities.Director;
import com.proyecto.entities.Film;
import com.proyecto.entities.Genre;
import com.proyecto.entities.Rate;

@Service
public class FilmServiceImpl implements IFilmService {

    @Autowired
    private IFilmDao filmDao;

    @Autowired
    private IGenreDao genreDao;

    @Autowired
    private IRateDao rateDao;

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

    @Override
    public List<Actor> getActors(int filmId) {
        Film film = filmDao.findById(filmId).get();
        return film.getActors();
    }

    @Override
    public List<Director> getDirectors(int filmId) {
        Film film = filmDao.findById(filmId).get();
        return film.getDirectors();
    }

    @Override
    public List<Film> getFilmByString(String string) {
        return filmDao.getFilmByString(string);
    }

    @Override
    public List<Genre> getGenres() {
        return genreDao.findAll();
    }

    @Override
    public List<Rate> getRates(int filmId) {
        Film film = filmDao.findById(filmId).get();
        return film.getRates();
    }

    @Override
    public int getAverageRate(int filmId) {
        Film film = filmDao.findById(filmId).get();
        List<Rate> rates = film.getRates();
        List<String> ratesString = new ArrayList<>();
       
        for (Rate r : rates) {
            ratesString.add(r.getRate().toString());
        }

        List<Integer> ratesInteger = new ArrayList<>();
        for (String s : ratesString) {
            ratesInteger.add(Integer.parseInt(s));
        }
        
        int average = 0;
        int acumulador = 0; 
        if (!ratesInteger.isEmpty()) {
       
            for (Integer i: ratesInteger) {
              acumulador = acumulador + i;
            }

            average = acumulador / (rates.size());
        }
        
        return average;
    }

    @Override
    public List<Rate> getRates() {
        return rateDao.findAll();
    }

    @Override
    public void saveRate(int filmId, Rate rate) {
        Film film = filmDao.getById(filmId);
        List<Rate> rates = film.getRates();
        rates.add(rate);
    }
}
