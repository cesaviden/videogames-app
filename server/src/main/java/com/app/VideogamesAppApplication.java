package com.app;

import com.app.auth.entities.PermissionEntity;
import com.app.auth.entities.RoleEntity;
import com.app.auth.entities.RoleEnum;
import com.app.auth.entities.UserEntity;
import com.app.auth.repositories.UserRepository;
import com.app.videogames.entities.VideogameEntity;
import com.app.videogames.repositories.VideogameRepository;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;
import java.util.Set;

@SpringBootApplication
public class VideogamesAppApplication {

        public static void main(String[] args) {
                SpringApplication.run(VideogamesAppApplication.class, args);
        }

        @SuppressWarnings("null")
        @Bean
        CommandLineRunner init(UserRepository userRepository, VideogameRepository videogameRepository) {
                
                return args -> {
                        /* Create PERMISSIONS */
                        PermissionEntity createPermission = PermissionEntity.builder()
                                        .name("CREATE")
                                        .build();

                        PermissionEntity readPermission = PermissionEntity.builder()
                                        .name("READ")
                                        .build();

                        PermissionEntity updatePermission = PermissionEntity.builder()
                                        .name("UPDATE")
                                        .build();

                        PermissionEntity deletePermission = PermissionEntity.builder()
                                        .name("DELETE")
                                        .build();

                        PermissionEntity refactorPermission = PermissionEntity.builder()
                                        .name("REFACTOR")
                                        .build();

                        /* Create ROLES */
                        RoleEntity roleAdmin = RoleEntity.builder()
                                        .roleEnum(RoleEnum.ADMIN)
                                        .permissionList(Set.of(createPermission, readPermission, updatePermission,
                                                        deletePermission))
                                        .build();

                        RoleEntity roleUser = RoleEntity.builder()
                                        .roleEnum(RoleEnum.USER)
                                        .permissionList(Set.of(createPermission, readPermission))
                                        .build();

                        RoleEntity roleInvited = RoleEntity.builder()
                                        .roleEnum(RoleEnum.INVITED)
                                        .permissionList(Set.of(readPermission))
                                        .build();

                        RoleEntity roleDeveloper = RoleEntity.builder()
                                        .roleEnum(RoleEnum.DEVELOPER)
                                        .permissionList(Set.of(createPermission, readPermission, updatePermission,
                                                        deletePermission, refactorPermission))
                                        .build();

                        /* CREATE USERS */
                        UserEntity userSantiago = UserEntity.builder()
                                        .username("santiago")
                                        .password("$2a$10$cMY29RPYoIHMJSuwRfoD3eQxU1J5Rww4VnNOUOAEPqCBshkNfrEf6")
                                        .email("santiago@vg.com")
                                        .name("Santiago")
                                        .surname("Gonzalez")
                                        .isEnabled(true)
                                        .accountNoExpired(true)
                                        .accountNoLocked(true)
                                        .credentialNoExpired(true)
                                        .roles(Set.of(roleAdmin))
                                        .build();

                        UserEntity userDaniel = UserEntity.builder()
                                        .username("daniel")
                                        .password("$2a$10$cMY29RPYoIHMJSuwRfoD3eQxU1J5Rww4VnNOUOAEPqCBshkNfrEf6")
                                        .email("daniel@vg.com")
                                        .name("Daniel")
                                        .surname("Gonzalez")
                                        .isEnabled(true)
                                        .accountNoExpired(true)
                                        .accountNoLocked(true)
                                        .credentialNoExpired(true)
                                        .roles(Set.of(roleUser))
                                        .build();

                        UserEntity userAndrea = UserEntity.builder()
                                        .username("andrea")
                                        .password("$2a$10$cMY29RPYoIHMJSuwRfoD3eQxU1J5Rww4VnNOUOAEPqCBshkNfrEf6")
                                        .email("andrea@vg.com")
                                        .name("Andrea")
                                        .surname("Gonzalez")
                                        .isEnabled(true)
                                        .accountNoExpired(true)
                                        .accountNoLocked(true)
                                        .credentialNoExpired(true)
                                        .roles(Set.of(roleInvited))
                                        .build();

                        UserEntity userAnyi = UserEntity.builder()
                                        .username("anyi")
                                        .password("$2a$10$cMY29RPYoIHMJSuwRfoD3eQxU1J5Rww4VnNOUOAEPqCBshkNfrEf6")
                                        .email("anyi@vg.com")
                                        .name("Anyi")
                                        .surname("Gonzalez")
                                        .isEnabled(true)
                                        .accountNoExpired(true)
                                        .accountNoLocked(true)
                                        .credentialNoExpired(true)
                                        .roles(Set.of(roleDeveloper))
                                        .build();

                        /* CREATE VIDEOGAMES */

                        VideogameEntity videogame1 = VideogameEntity.builder()
                                        .name("Super Mario 64")
                                        .description("Juego de aventura de la serie Super Mario")
                                        .company("Nintendo")
                                        .year(1996)
                                        .price(59.99)
                                        .build();

                        VideogameEntity videogame2 = VideogameEntity.builder()
                                        .name("The Legend of Zelda: Breath of the Wild")
                                        .description("Juego de aventura de la serie The Legend of Zelda")
                                        .company("Nintendo")
                                        .year(2017)
                                        .price(29.99)
                                        .build();

                        VideogameEntity videogame3 = VideogameEntity.builder()
                                        .name("The Legend of Zelda: Ocarina of Time")
                                        .description("Juego de aventura de la serie The Legend of Zelda")
                                        .company("Nintendo")
                                        .year(1996)
                                        .price(39.99)
                                        .build();

                        VideogameEntity videogame4 = VideogameEntity.builder()
                                        .name("The Legend of Zelda: Oracle of Ages")
                                        .description("Juego de aventura de la serie The Legend of Zelda")
                                        .company("Nintendo")
                                        .year(2011)
                                        .price(19.99)
                                        .build();

                        VideogameEntity videogame5 = VideogameEntity.builder()
                                        .name("The Legend of Zelda: Majora's Mask")
                                        .description("Juego de aventura de la serie The Legend of Zelda")
                                        .company("Nintendo")
                                        .year(2000)
                                        .price(10.99)
                                        .build();
                        
                        /* SAVE USERS */
                        userRepository.saveAll(List.of(userSantiago, userDaniel, userAndrea, userAnyi));

                        /* SAVE VIDEOGAMES */
                        videogameRepository.saveAll(List.of(videogame1, videogame2, videogame3, videogame4, videogame5));
                };
        }
}