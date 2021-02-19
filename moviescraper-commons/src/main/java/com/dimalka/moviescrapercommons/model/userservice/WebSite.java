package com.dimalka.moviescrapercommons.model.userservice;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@ToString
public class WebSite {
    @Id
    @GeneratedValue
    private int websiteId;
    private String url;
}
