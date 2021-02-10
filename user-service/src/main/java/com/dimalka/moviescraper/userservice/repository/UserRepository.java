package com.dimalka.moviescraper.userservice.repository;

import com.dimalka.moviescrapercommons.model.userservice.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;

public interface UserRepository extends JpaRepository<User, Integer> {

    User findByUserEmail(String userEmail);

    @Modifying
    @Transactional
    @Query("update User u set u.firstName=?3, u.userEmail=?2, u.lastName=?4, u.imgUrl=?5 where u.id=?1")
    int updateUser(int id, String userEmail, String firstName, String lastName, String imgUrl);

}
