package com.dimalka.moviescrapercommons.model.repositoryservice;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
public class Genre {
    @Id
    @GeneratedValue
    private int id;
    private String name;

}
