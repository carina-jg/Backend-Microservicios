package com.dh.apiseries.controller;

import com.dh.apiseries.model.Serie;
import com.dh.apiseries.service.SerieService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/series")
public class SerieController {

    private final SerieService serieService;

    public SerieController(SerieService serieService) {
        this.serieService = serieService;
    }

    @GetMapping()
    private ResponseEntity<List<Serie>> listSeries(){
        return ResponseEntity.ok(serieService.getAll());
    }

    @GetMapping("/{genre}")
    ResponseEntity<List<Serie>> getSeriesByGenre(@PathVariable String genre) {
        return ResponseEntity.ok().body(serieService.findByGenre(genre));
    }

    @PostMapping("/save")
    ResponseEntity<Serie> saveSerie(@RequestBody Serie serie) {
        return ResponseEntity.ok().body(serieService.save(serie));
    }

    @DeleteMapping("/{id}")
    ResponseEntity deleteSerie(@PathVariable String id) {
        serieService.deleteById(id);
        return ResponseEntity.ok().build();
    }


}
