package com.dh.catalog.event;

import com.dh.catalog.config.RabbitMQConfig;
import com.dh.catalog.model.Serie;
import com.dh.catalog.repository.SerieRepository;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;

@Component
public class NewSerieEventConsumer {

    private SerieRepository serieRepository;


    public NewSerieEventConsumer(SerieRepository serieRepository) {
        this.serieRepository = serieRepository;
    }

    @RabbitListener(queues = RabbitMQConfig.QUEUE_NEW_SERIE)
    public void execute(NewSerieEventConsumer.Data data){
        Serie serieNew = new Serie();
        BeanUtils.copyProperties(data.getSerie(), serieNew);
        serieRepository.save(serieNew);


    }


    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor

    public static class Data implements Serializable {

        @Serial
        private static final long serialVersionUID = 1L;
        private Data.SerieDTO serie = new Data.SerieDTO();

        @Getter
        @Setter
        @NoArgsConstructor
        @AllArgsConstructor
        public static class SerieDTO implements Serializable {

            @Serial
            private static final long serialVersionUID = 1L;
            private String serieId;
            private String name;
            private String genre;
            private List<Data.SerieDTO.SeasonDTO> seasons;

            @Getter
            @Setter
            @NoArgsConstructor
            @AllArgsConstructor
            public static class SeasonDTO implements Serializable {

                @Serial
                private static final long serialVersionUID = 1L;
                private String seasonId;
                private Integer seasonNumber;
                private List<Data.SerieDTO.SeasonDTO.ChapterDTO> chapters;

                @Getter
                @Setter
                @NoArgsConstructor
                @AllArgsConstructor
                public static class ChapterDTO implements Serializable {

                    @Serial
                    private static final long serialVersionUID = 1L;
                    private String chapterId;
                    private String name;
                    private Integer chapterNumber;
                    private String urlStream;
                }
            }
        }
    }
}
