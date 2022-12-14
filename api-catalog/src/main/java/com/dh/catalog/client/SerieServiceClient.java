package com.dh.catalog.client;

import com.dh.catalog.configuration.LoadBalancerConfiguration;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.cloud.loadbalancer.annotation.LoadBalancerClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name="api-serie")
@LoadBalancerClient(name = "api-serie", configuration = LoadBalancerConfiguration.class)
public interface SerieServiceClient {

    @GetMapping("/api/v1/series/{genre}")
    List<SerieDto> getSerieByGenre(@PathVariable (value = "genre") String genre);

    @Getter
    @Setter
    @AllArgsConstructor
    class SerieDto{
        private String serieId;
        private String name;
        private String genre;
        private List<SeasonDto> seasons;
    }
    @Getter
    @Setter
    @AllArgsConstructor
    class SeasonDto{
        private String seasonId;
        private Integer seasonNumber;
        private List<ChapterDto> chapters;
    }

    @Getter
    @Setter
    @AllArgsConstructor
    class ChapterDto{
        private String chapterId;
        private String name;
        private Integer chapterNumber;
        private String urlStream;
    }
}
