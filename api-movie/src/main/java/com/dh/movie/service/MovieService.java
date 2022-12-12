package com.dh.movie.service;

import com.dh.movie.event.NewMovieEventProducer;
import com.dh.movie.model.Movie;
import com.dh.movie.repository.MovieRepository;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MovieService {

    private final MovieRepository movieRepository;
    private final NewMovieEventProducer newMovieEventProducer;

    final static Logger log = Logger.getLogger(MovieService.class);

    public MovieService(MovieRepository movieRepository, NewMovieEventProducer newMovieEventProducer) {
        this.movieRepository = movieRepository;
        this.newMovieEventProducer = newMovieEventProducer;
    }

    public List<Movie> findByGenre(String genre) {
        log.info("Obteniendo las películas del género "+ genre);
        return movieRepository.findByGenre(genre);
    }

    public Movie save(Movie movie) {
        log.info("Guardando la película " + movie.getName());
        movieRepository.save(movie);
        newMovieEventProducer.execute(movie);
        return movie;
    }

    public List<Movie> getAll() {
        log.info("Obteniendo todas las películas");
        return movieRepository.findAll();
    }

    public Movie getMovieById(Long id) {
        log.info("Obteniendo la película con id " + id);
        return movieRepository.findById(id).orElse(null);
    }

    public void updateMovie(Movie movie) {
        log.info("Actualizando la película con id" + movie.getId());
        if(movieRepository.existsById(movie.getId())){
            movieRepository.save(movie);
        }
    }

    public void deleteById(Long id) {
        log.info("Eliminando la película con " + id);
        movieRepository.deleteById(id);
    }
}
