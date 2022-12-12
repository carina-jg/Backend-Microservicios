package com.dh.apiseries;

import com.dh.apiseries.model.Chapter;
import com.dh.apiseries.model.Season;
import com.dh.apiseries.model.Serie;
import com.dh.apiseries.repository.SerieRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
@EnableDiscoveryClient
@EnableMongoRepositories
public class ApiSeriesApplication {

	public static void main(String[] args) {
		SpringApplication.run(ApiSeriesApplication.class, args);
	}


	@Bean
	public CommandLineRunner loadData(SerieRepository repository) {
		return (args) -> {
			if (!repository.findAll().isEmpty()) {
				return;
			}

			Chapter chapterOne = new Chapter("C_001", "Chapter 1", 1, "www.chapter1.com");
			Chapter chapterTwo = new Chapter("C_002", "Chapter 2", 2, "www.chapter1.com");
			Chapter chapterThree = new Chapter("C_003", "Chapter 3", 3, "www.chapter1.com");
			List<Chapter> chapters = new ArrayList<>();
			chapters.add(chapterOne);
			chapters.add(chapterTwo);
			chapters.add(chapterThree);

			Season seasonOne = new Season("SEASON_001", 1, chapters);
			Season seasonTwo = new Season("SEASON_002", 2, chapters);
			Season seasonThree = new Season("SEASON_003", 3, chapters);
			List<Season> seasons = new ArrayList<>();
			seasons.add(seasonOne);
			seasons.add(seasonTwo);
			seasons.add(seasonThree);

			Serie serieOne = new Serie("SERIE_001", "Season 1", "drama", seasons);
			Serie serieTwo = new Serie("SERIE_002", "Season 2", "drama", seasons);
			Serie serieThree = new Serie("SERIE_003", "Season 3", "comedia", seasons);
			Serie serieFor = new Serie("SERIE_004", "Season 4", "comedia", seasons);

			repository.save(serieOne);
			repository.save(serieTwo);
			repository.save(serieThree);
			repository.save(serieFor);
		};
	}
}
