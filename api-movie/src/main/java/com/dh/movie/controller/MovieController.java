package com.dh.movie.controller;

import com.dh.movie.model.Movie;
import com.dh.movie.service.MovieService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/movies")
public class MovieController {


    private final MovieService movieService;

    public MovieController(MovieService movieService) {
        this.movieService = movieService;
    }

    @GetMapping("/{genre}")
    ResponseEntity<List<Movie>> getMovieByGenre(@PathVariable String genre) {
        return ResponseEntity.ok().body(movieService.findByGenre(genre));
    }

    @PostMapping("/save")
    ResponseEntity<Movie> saveMovie(@RequestBody Movie movie) {
        return ResponseEntity.ok().body(movieService.save(movie));
    }

    @GetMapping
    ResponseEntity<List<Movie>> listMovies() {
        return ResponseEntity.ok(movieService.getAll());
    }

    @GetMapping("/find/{id}")
    ResponseEntity<Movie> getMovieById(@PathVariable Long id) {
        return ResponseEntity.ok().body(movieService.getMovieById(id));
    }

    @PutMapping
    ResponseEntity updateMovie(@RequestBody Movie movie) {
        movieService.updateMovie(movie);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    ResponseEntity deleteMovie(@PathVariable Long id) {
        movieService.deleteById(id);
        return ResponseEntity.ok().build();
    }
}
