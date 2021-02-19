package com.dimalka.moviescrapercommons.model.userservice;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "user")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class User {
    @Id
    @GeneratedValue
    private int id;
    private String username;
    private String userEmail;
    private String firstName;
    private String lastName;
    private String imgUrl;
    @ManyToMany
    private List<Genre> genres;
    @ManyToMany
    private List<WebSite> webSites;
}
/*

{
    "userEmail":"dimalka@dimalka.lk",
    "firstName":"Dimalka",
    "lastName":"Perera",
    "imgUrl":"url"
}

 */