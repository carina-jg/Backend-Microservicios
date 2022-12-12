package com.dh.catalog;


import com.dh.catalog.model.Movie;
import com.dh.catalog.model.Serie;
import io.restassured.http.ContentType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertEquals;

class MovieSerieTest extends BaseAPI {

    @Test
    @Tag("functional")
    @DisplayName("Testear toda la aplicacion con api gateway")
    void testingAll() {

        given().
                contentType(ContentType.JSON).
                body(
                        new MovieRequest(160L, "Nombre", "Genero", "www.url.com")
                ).
                when().post("/api/v1/movies");

        given().
                contentType(ContentType.JSON).
                body(
                        new SerieRequest("S150", "NombreSerie", "Ficción")
                ).
                when().post("/api/v1/series");

        Movie responseOnlineMovie = given()
                .pathParam("movies", "genre")
                .when().get("/api/v1/catalog/online/movies/{genre}")
                .andReturn().body().as(Movie.class);

        Movie responseOfflineMovie = given()
                .pathParam("moviesOffline", "genre")
                .when().get("/api/v1/catalog/offline/movies/{genre}")
                .andReturn().body().as(Movie.class);

        Serie responseOnlineSerie = given()
                .pathParam("series", "genre")
                .when().get("/api/v1/catalog/online/series/{genre}")
                .andReturn().body().as(Serie.class);

        Serie responseOfflineSerie = given()
                .pathParam("seriesOffline", "genre")
                .when().get("/api/v1/catalog/offline/series/{genre}")
                .andReturn().body().as(Serie.class);


        assertEquals(responseOnlineMovie.getGenre(), 1);
        assertEquals(responseOnlineSerie.getGenre(), 1);

        assertEquals(responseOfflineMovie.getGenre(), 1);
        assertEquals(responseOfflineSerie.getGenre(), 1);


    }

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class MovieRequest {
        private Long id;
        private String name;
        private String genre;
        private String urlStream;
    }

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class SerieRequest {
        private String id;
        private String name;
        private String genre;;
    }
}
