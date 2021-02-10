package com.dimalka.moviescraper.scrapingservice.website;

import java.util.*;

public class MovieGenre {
    private Map<String, List<String>> genres;

    public MovieGenre() {
        genres = new HashMap<>();
        genres.put("WAR", Arrays.asList("War", "යුද්ධ"));
        genres.put("ROMANCE", Arrays.asList("Romance", "යුද්ධ"));
        genres.put("ACTION", Arrays.asList("War", "ක්‍රියාදාම"));
        genres.put("COMEDY", Arrays.asList("War", "කොමඩියක්"));
        genres.put("ANIMATION", Arrays.asList("War", "ඇනිමේෂන්"));
        genres.put("CRIME", Arrays.asList("War", "අපරාධ"));
        genres.put("DRAMA", Arrays.asList("War", "නාට්\u200Dයමය"));
    }
}
