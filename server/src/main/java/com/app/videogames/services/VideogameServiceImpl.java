package com.app.videogames.services;

import com.app.videogames.controllers.dtos.VideogameDTO;
import com.app.videogames.controllers.dtos.VideogameResponse;
import com.app.videogames.entities.VideogameEntity;
import com.app.videogames.repositories.VideogameRepository;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class VideogameServiceImpl implements VideogameService {

    @Autowired
    private VideogameRepository videogameRepository;

    @Override
    public ResponseEntity<?> save(VideogameDTO videogame) {
        try {
            if(videogame.name() == null) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error saving videogame. Videogame name is required.");
            } else if(videogame.price() == null) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error saving videogame. Videogame price is required.");
            } else if(videogame.description() == null) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error saving videogame. Videogame description is required.");
            } else if(videogame.company() == null) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error saving videogame. Videogame company is required.");
            } else if(videogame.year() == null) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error saving videogame. Videogame year is required.");
            }
            VideogameEntity videogameEntity = convertToEntity(videogame);
            videogameRepository.save(videogameEntity);
            return ResponseEntity.status(HttpStatus.CREATED).body(new VideogameResponse("Videogame saved successfully", videogame, true));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error saving videogame");
        }
    }

    @Override
    public ResponseEntity<?> getAll() {
        try {
            List<VideogameEntity> videogames = videogameRepository.findAll();
            if (videogames.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Not found videogames");
            }
            List<VideogameDTO> videogamesDTO = videogames
                    .stream()
                    .map(this::convertToDTO)
                    .collect(Collectors.toList());
            return ResponseEntity.status(HttpStatus.OK).body(videogamesDTO);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Collections.emptyList());
        }
    }

    @Override
    public ResponseEntity<?> getById(Integer id) {
        try {
            Optional<VideogameEntity> videogameEntity = videogameRepository.findById(id);

            Optional<VideogameDTO> videogameDTO = Optional.ofNullable(convertToDTO(videogameEntity.get()));
            return ResponseEntity.status(HttpStatus.OK).body(videogameDTO);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Not found videogame with id: " + id);
        }
    }

    @Override
    public ResponseEntity<?> update(Integer id, VideogameDTO videogame) {
        try {
            // Obtener el videojuego existente de la base de datos
            Optional<VideogameEntity> optionalVideogame = videogameRepository.findById(id);
            if (optionalVideogame.isPresent()) {
                VideogameEntity updatedVideogame = optionalVideogame.get();
    
                // Verificar y actualizar el nombre
                if (videogame.name() != null) {
                    updatedVideogame.setName(videogame.name());
                }
    
                // Verificar y actualizar la compañía
                if (videogame.company() != null) {
                    updatedVideogame.setCompany(videogame.company());
                }
    
                // Verificar y actualizar el año
                if (videogame.year() != null) {
                    updatedVideogame.setYear(videogame.year());
                }
    
                // Verificar y actualizar la descripción
                if (videogame.description() != null) {
                    updatedVideogame.setDescription(videogame.description());
                }
    
                // Verificar y actualizar el precio
                if (videogame.price() != null) {
                    updatedVideogame.setPrice(videogame.price());
                }
    
                // Guardar el videojuego actualizado en la base de datos
                videogameRepository.save(updatedVideogame);
    
                return ResponseEntity.status(HttpStatus.OK).body("Videogame updated successfully with id: " + id);
            } else {
                // Manejar el caso en que no se encuentre el videojuego con el ID proporcionado
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Videogame not found with id: " + id);
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error updating videogame with id: " + id + ". Reason: " + e.getMessage());
        }
    }    
    
    @SuppressWarnings("unused")
    @Override
    public ResponseEntity<?> deleteById(Integer id) {

        try {
            Optional<VideogameEntity> videogameEntity = videogameRepository.findById(id);
            videogameRepository.deleteById(id);
            return ResponseEntity.status(HttpStatus.OK).body("Videogame deleted successfully");

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Not found videogame with id: " + id);
        }

    }

    @Override
    public ResponseEntity<?> getAllByYear(Integer year) {

        try {
            Optional<List<VideogameEntity>> videogames = Optional.ofNullable(videogameRepository.findAllByYear(year));

            if (!videogames.isEmpty()) {
                return ResponseEntity.status(HttpStatus.OK).body(videogames.get()
                        .stream()
                        .map(this::convertToDTO)
                        .collect(Collectors.toList()));
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Not found videogame with year: " + year);
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error getting videogames with year: " + year);
        }
    }

    @Override
    public ResponseEntity<?> getAllByCompany(String company) {

        try {
            Optional<List<VideogameEntity>> videogames = Optional
                    .ofNullable(videogameRepository.findAllByCompany(company));

            if (!videogames.isEmpty()) {
                return ResponseEntity.status(HttpStatus.OK).body(videogames.get()
                        .stream()
                        .map(this::convertToDTO)
                        .collect(Collectors.toList()));
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body("Not found videogame or videogames with company: " + company);
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Not found videogame or videogames with company: " + company);
        }
    }

    private VideogameDTO convertToDTO(VideogameEntity videogameEntity) {
        return new VideogameDTO(
                videogameEntity.getId(),
                videogameEntity.getName(),
                videogameEntity.getDescription(),
                videogameEntity.getYear(),
                videogameEntity.getCompany(),
                videogameEntity.getPrice());
    }

    private VideogameEntity convertToEntity(VideogameDTO videogameDTO) {
        return VideogameEntity
                .builder()
                .id(videogameDTO.id())
                .name(videogameDTO.name())
                .description(videogameDTO.description())
                .year(videogameDTO.year())
                .company(videogameDTO.company())
                .price(videogameDTO.price())
                .build();
    }
}
