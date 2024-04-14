package com.app.videogames.services;

import com.app.videogames.controllers.dtos.VideogameDTO;
import org.springframework.http.ResponseEntity;

public interface VideogameService {

    ResponseEntity<?> save(VideogameDTO videogame);
    ResponseEntity<?> getAll();
    ResponseEntity<?> getById(Integer id);
    ResponseEntity<?> update(Integer id, VideogameDTO videogame);
    ResponseEntity<?> deleteById(Integer id);
    ResponseEntity<?> getAllByYear(Integer year);
    ResponseEntity<?> getAllByCompany(String company);
}
