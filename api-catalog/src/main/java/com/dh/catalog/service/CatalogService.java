package com.dh.catalog.service;

import com.dh.catalog.client.MovieServiceClient;
import com.dh.catalog.client.SerieServiceClient;
import com.dh.catalog.model.Movie;
import com.dh.catalog.model.Serie;
import com.dh.catalog.repository.MovieRepository;
import com.dh.catalog.repository.SerieRepository;
import io.github.resilience4j.circuitbreaker.CallNotPermittedException;
import org.springframework.stereotype.Service;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;

import java.util.List;

@Service
public class CatalogService {

    private final MovieRepository movieRepository;
    private final SerieRepository serieRepository;
    private final MovieServiceClient movieServiceClient;
    private final SerieServiceClient serieServiceClient;


    public CatalogService(MovieRepository movieRepository, SerieRepository serieRepository, MovieServiceClient movieServiceClient, SerieServiceClient serieServiceClient) {
        this.movieRepository = movieRepository;
        this.serieRepository = serieRepository;
        this.movieServiceClient = movieServiceClient;
        this.serieServiceClient = serieServiceClient;
    }

    //Offline
    public List<Movie> findMovieByGenre(String genre) {
        return movieRepository.findByGenre(genre);
    }

    public List<Serie> findSerieByGenre(String genre) {
        return serieRepository.findByGenre(genre);
    }

    // Online
    // Aplico Circuit Breaker y Retry para que en el caso de que los servicios no respondan
    // cuando se hace una consulta online, se vuelva a probar algunas veces más, y en el caso
    // de que siga sin funcionar, tome un método alternativo (en este caso, devuelve los
    // mismos datos que se tienen guardados para la consulta offline, para devolverle una respuesta
    // amigable al usuario).
    @CircuitBreaker(name = "clientMovie", fallbackMethod = "findMovieFallBack")
    @Retry(name = "retryMovie")
    public List<?> getMoviesByGenre(String genre) {
        return movieServiceClient.getMovieByGenre(genre);
    }

    public List<?> findMovieFallBack(CallNotPermittedException ex, String genre) {
        return findMovieByGenre(genre);
    }

    @CircuitBreaker(name = "clientSerie", fallbackMethod = "findSerieFallBack")
    @Retry(name = "retrySerie")
    public List<?> getSeriesByGenre(String genre) {
        return serieServiceClient.getSerieByGenre(genre);
    }

    public List<?> findSerieFallBack(CallNotPermittedException ex, String genre) {
        return findSerieByGenre(genre);
    }

}
