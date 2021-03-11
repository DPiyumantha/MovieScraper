package com.dimalka.authorizationservernew.controller;

import com.dimalka.authorizationservernew.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;
import com.dimalka.authorizationservernew.model.User;

@RestController
public class MainController {
    Logger log = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private UserService userService;

    @PostMapping("/user/register")
    public ResponseEntity<?> greet(@RequestBody User user) {
        System.out.println(user);
        try {
            return new ResponseEntity(userService.registerUser(user), HttpStatus.OK);
        } catch (DuplicateKeyException e) {
            log.error(e.getLocalizedMessage());
            return new ResponseEntity(e.getLocalizedMessage(), HttpStatus.BAD_REQUEST);
        }
    }

}
