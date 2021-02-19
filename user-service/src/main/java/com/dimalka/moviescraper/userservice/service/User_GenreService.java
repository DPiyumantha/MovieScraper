package com.dimalka.moviescraper.userservice.service;

import org.springframework.stereotype.Service;

@Service
public class User_GenreService {
    /*
    @Autowired
    User_GenreRepository user_genreRepository;
    @Autowired
    GenreService genreService;

    public User_Genre saveUserGenre(User_Genre user_genre) {
        return user_genreRepository.save(user_genre);
    }

    public List<User_Genre> getAllUser_Genre() {
        return user_genreRepository.findAll();
    }

    public List<Genre> getGenresByUserId(int id) {
        List<Genre> list = new ArrayList<>();
        List<User_Genre> usergenres = user_genreRepository.findAllByUserId(id);
        List<Integer> genreIds = new ArrayList<>();
        usergenres.stream().forEach(i->{
            genreIds.add(i.getGenreId());
        });
        genreIds.stream().forEach(item -> {
            list.add(genreService.getGenreById(item));
        });
        return list;
    }

     */
}
