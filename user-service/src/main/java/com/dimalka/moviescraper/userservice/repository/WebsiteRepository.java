package com.dimalka.moviescraper.userservice.repository;

import com.dimalka.moviescrapercommons.model.userservice.WebSite;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WebsiteRepository extends JpaRepository<WebSite, Integer> {
}
