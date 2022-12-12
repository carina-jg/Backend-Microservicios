package com.dh.catalog.controller;

import com.dh.catalog.model.Movie;
import com.dh.catalog.model.Serie;
import com.dh.catalog.service.CatalogService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/catalog")
public class CatalogController {

	private final CatalogService catalogService;

	public CatalogController(CatalogService catalogService) {
		this.catalogService = catalogService;
	}

	@GetMapping("/online/movies/{genre}")
	ResponseEntity<List<?>> getMovieByGenreOnline(@PathVariable String genre) {
		return ResponseEntity.ok(catalogService.getMoviesByGenre(genre));
	}

	@GetMapping("/online/series/{genre}")
	ResponseEntity<List<?>> getSerieByGenreOnline(@PathVariable String genre) {
		return ResponseEntity.ok(catalogService.getSeriesByGenre(genre));
	}

	@GetMapping("/offline/movies/{genre}")
	ResponseEntity<List<Movie>> getMovieOffline(@PathVariable String genre){
		return ResponseEntity.ok(catalogService.findMovieByGenre(genre));
	}

	@GetMapping("/offline/series/{genre}")
	ResponseEntity<List<Serie>> getSerieOffine(@PathVariable String genre){
		return ResponseEntity.ok(catalogService.findSerieByGenre(genre));
	}

}
