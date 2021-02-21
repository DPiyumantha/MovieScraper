package com.dimalka.moviescraper.movierepositoryservice.repository;

import com.dimalka.moviescrapercommons.model.repositoryservice.MovieRecord;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MovieRepository extends JpaRepository<MovieRecord, Integer> {


    List<MovieRecord> findAllByName(String name);
    List<MovieRecord> findAllByUserId(int id);

}
