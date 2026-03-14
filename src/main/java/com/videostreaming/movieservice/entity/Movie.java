package com.videostreaming.movieservice.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name ="movies")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Movie {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String title;
    private String description;
    private String genre;
    private int duration;
    private String videoUrl;


}
