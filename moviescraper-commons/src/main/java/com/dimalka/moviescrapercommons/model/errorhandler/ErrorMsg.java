package com.dimalka.moviescrapercommons.model.errorhandler;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
@Data
public class ErrorMsg {
    String errTitle;
    String errDesc;
    String timeStamp;
public ErrorMsg(String errTitle){
    this.errTitle=errTitle;
}

}
