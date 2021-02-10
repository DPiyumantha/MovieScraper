package com.dimalka.moviescrapercommons.model.repositoryservice;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.List;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Movie implements Cloneable {
    @Id
    @GeneratedValue
    int id;
    String name;
    String link;
    String imdb;
    String img;




    @Override
    public Object clone() throws CloneNotSupportedException {
        return super.clone();

    }


}
