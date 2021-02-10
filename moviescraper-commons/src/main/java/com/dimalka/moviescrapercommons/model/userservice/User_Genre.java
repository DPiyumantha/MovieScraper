package com.dimalka.moviescrapercommons.model.userservice;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class User_Genre {
    @Id
    @GeneratedValue
    private int id;
    private int userId;
    private int genreId;
}
