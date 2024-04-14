package com.app.videogames.controllers.dtos;

public record VideogameDTO(
        Integer id,
        String name,
        String description,
        Integer year,
        String company,
        Double price) {

}
