package com.dimalka.moviescrapercommons.model.repositoryservice;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import java.util.List;
@Data
@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class MovieRecord implements Cloneable {
    @Id
    @GeneratedValue
    int id;
    int userId;
    String name;
    String link;
    String imdb;
    String img;

    @ManyToMany
    List<Genre> genres;




    @Override
    public Object clone() throws CloneNotSupportedException {
        return super.clone();

    }


}
