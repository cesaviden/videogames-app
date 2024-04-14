package com.app.videogames.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import com.app.videogames.entities.VideogameEntity;

public interface VideogameRepository extends JpaRepository<VideogameEntity, Integer> {

    List<VideogameEntity> findAllByYear(Integer year);
    List<VideogameEntity> findAllByCompany(String company);    
}
