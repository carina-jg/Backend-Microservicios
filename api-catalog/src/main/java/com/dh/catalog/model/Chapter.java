package com.dh.catalog.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serial;
import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString
@Document(collection = "Chapters")
public class Chapter implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    private String chapterId;
    private String name;
    private Integer number;
    private String urlStream;

}
