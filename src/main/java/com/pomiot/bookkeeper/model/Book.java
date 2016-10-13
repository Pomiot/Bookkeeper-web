package com.pomiot.bookkeeper.model;

import lombok.Data;
import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Entity
@Data
public class Book {

    @Id
    @GeneratedValue
    private Long id;

    @NotNull
    private String title;

    @NotNull
    private String author;

    @Column(columnDefinition = "TEXT")
    private String description;

    private String publisher;

    private Date publishedOn;

    private Boolean isLent;

    private Boolean isRead;

    @Min(1)
    @Max(10)
    private Integer rating;

    @NotNull
    @ManyToOne
    private User owner;

    public Book() {
    }
}
