package com.app.videogames.controllers.dtos;

public record VideogameResponse(
    String message,
    VideogameDTO videogame,
    boolean status
) {

}
