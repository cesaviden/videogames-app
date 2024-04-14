package com.app.videogames.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;


@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "videogames")
public class VideogameEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(unique = true, nullable = false)
    @NotBlank(message = "The name is required")
    private String name;

    @Column(nullable = false)
    @NotBlank(message = "The description is required")
    private String description;

    @Column(nullable = false)
    @NotNull(message = "The year is required")
    private Integer year;

    @Column(nullable = false)
    @NotBlank(message = "The company is required")
    private String company;

    @Column(nullable = false)
    @NotNull(message = "The price is required")
    private Double price;

}
