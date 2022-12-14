package com.dh.catalog.event;

import com.dh.catalog.config.RabbitMQConfig;
import com.dh.catalog.model.Movie;
import com.dh.catalog.repository.MovieRepository;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import java.io.Serial;
import java.io.Serializable;

@Component
public class NewMovieEventConsumer {

    private MovieRepository movieRepository;


    public NewMovieEventConsumer(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    @RabbitListener(queues = RabbitMQConfig.QUEUE_NEW_MOVIE)
    public void execute(NewMovieEventConsumer.Data data){
        Movie movieNew = new Movie();
        BeanUtils.copyProperties(data.getMovie(), movieNew);
        movieRepository.save(movieNew);
    }

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor

    public static class Data implements Serializable {

        @Serial
        private static final long serialVersionUID = 1L;
        private MovieDTO movie = new MovieDTO();

        @Getter
        @Setter
        @NoArgsConstructor
        @AllArgsConstructor
        public static class MovieDTO implements Serializable{

            @Serial
            private static final long serialVersionUID = 1L;
            private String movieId;
            private String name;
            private String genre;
            private String urlStream;
        }
    }
}
