package com.app.videogames.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.app.videogames.controllers.dtos.VideogameDTO;
import com.app.videogames.services.VideogameServiceImpl;

@RestController
@RequestMapping("/api/v1/videogames")
@Validated
@CrossOrigin(origins = {"http://localhost:4200"})
public class VideogameController {

    @Autowired
    private VideogameServiceImpl videogameService;

    @RequestMapping
    public ResponseEntity<?> getAllVideogames() {
        return videogameService.getAll();
    }

    @RequestMapping("/{id}")
    public ResponseEntity<?> getVideogameById(@PathVariable Integer id) {
        return videogameService.getById(id);
    }

    @RequestMapping("/year/{year}")
    public ResponseEntity<?> getVideogamesByYear(@PathVariable Integer year) {
        return videogameService.getAllByYear(year);
    }

    @RequestMapping("/company/{company}")
    public ResponseEntity<?> getVideogamesByCompany(@PathVariable String company) {
        return videogameService.getAllByCompany(company);
    }

    @PostMapping
    public ResponseEntity<?> createVideogame(@RequestBody VideogameDTO videogame) {
        return videogameService.save(videogame);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateVideogame(@PathVariable Integer id, @RequestBody VideogameDTO videogame) {
        return videogameService.update(id, videogame);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteVideogame(@PathVariable Integer id) {
        return videogameService.deleteById(id);
    }

}
