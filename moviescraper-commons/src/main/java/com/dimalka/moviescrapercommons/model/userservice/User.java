package com.dimalka.moviescrapercommons.model.userservice;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name="user")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class User {
    @Id
    @GeneratedValue
    private int id;

    private String userEmail;
    private String firstName;
    private String lastName;
    private String imgUrl;
}
/*

{
    "userEmail":"dimalka@dimalka.lk",
    "firstName":"Dimalka",
    "lastName":"Perera",
    "imgUrl":"url"
}

 */