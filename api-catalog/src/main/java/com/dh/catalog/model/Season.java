package com.dh.catalog.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString
@Document(collection = "Seasons")
public class Season implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    private String seasonId;
    private Integer seasonNumber;
    private List<Chapter> chapters;

}