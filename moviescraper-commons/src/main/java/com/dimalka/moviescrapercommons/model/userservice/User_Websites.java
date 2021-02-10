package com.dimalka.moviescrapercommons.model.userservice;



import lombok.*;

import java.util.List;
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@ToString
public class User_Websites {
    private int userId;
    private List<Integer> websites;
}
