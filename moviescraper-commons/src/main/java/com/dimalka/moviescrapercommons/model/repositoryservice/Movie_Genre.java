package com.dimalka.moviescrapercommons.model.repositoryservice;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Movie_Genre {
    @Id
    @GeneratedValue
    int id;
    int movieId;
    int genreId;
}
