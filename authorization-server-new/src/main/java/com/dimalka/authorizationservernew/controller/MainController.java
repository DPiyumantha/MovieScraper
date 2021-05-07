package com.dimalka.authorizationservernew.controller;

import com.dimalka.authorizationservernew.service.UserService;
import com.dimalka.moviescrapercommons.model.errorhandler.ErrorMsg;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;
import com.dimalka.authorizationservernew.model.User;

import java.text.SimpleDateFormat;
import java.util.Date;

@RestController
public class MainController {
    Logger log = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private UserService userService;

    @GetMapping("/test")
    public String sayHello(){
        return "Hello";
    }

    @PostMapping("/user/register")
    public ResponseEntity<?> greet(@RequestBody User user) {
        try {
            return new ResponseEntity(userService.registerUser(user), HttpStatus.OK);
        } catch (DuplicateKeyException e) {
            log.error(e.getLocalizedMessage());
            return new ResponseEntity(
                    new ErrorMsg(e.getLocalizedMessage(),
                            "",
                            new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date())), HttpStatus.CONFLICT);
        }
    }

}
