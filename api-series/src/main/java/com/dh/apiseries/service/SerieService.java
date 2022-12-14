package com.dh.apiseries.service;

import com.dh.apiseries.event.NewSerieEventProducer;
import com.dh.apiseries.model.Serie;
import com.dh.apiseries.repository.SerieRepository;
import lombok.extern.slf4j.Slf4j;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class SerieService {

    private final SerieRepository serieRepository;
    private final NewSerieEventProducer newSerieEventProducer;

    //final static Logger log = Logger.getLogger(SerieService.class);

    public SerieService(SerieRepository serieRepository, NewSerieEventProducer newSerieEventProducer) {
        this.serieRepository = serieRepository;
        this.newSerieEventProducer = newSerieEventProducer;
    }

    public List<Serie> getAll(){
        log.info("Obteniendo todas las series");
        return serieRepository.findAll();
    }

    public List<Serie> findByGenre(String genre) {
        log.info("Obteniendo las series del género "+ genre);
        return serieRepository.findByGenre(genre);
    }

    public Serie save(Serie serie) {
        log.info("Guardando la serie " + serie.getName());
        Serie savedSerie = serieRepository.save(serie);
        newSerieEventProducer.execute(savedSerie);
        return savedSerie;
    }

    public void deleteById(String id) {
        log.info("Eliminando la serie con " + id);
        serieRepository.deleteById(id);
    }

}
