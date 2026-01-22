package com.bootcamp.devops.controller;

import com.bootcamp.devops.dto.PersonaRequest;
import com.bootcamp.devops.dto.PersonaResponse;
import com.bootcamp.devops.service.PersonaService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class PersonaController {
    
    private final PersonaService personaService;
    
    @PostMapping("/guardarpersona")
    public Mono<ResponseEntity<PersonaResponse>> guardarPersona(@Valid @RequestBody PersonaRequest request) {
        return personaService.guardarPersona(request)
                .map(persona -> ResponseEntity.status(HttpStatus.CREATED).body(persona))
                .onErrorResume(error -> {
                    if (error.getMessage() != null && error.getMessage().contains("ya existe")) {
                        return Mono.just(ResponseEntity.status(HttpStatus.CONFLICT)
                                .body(PersonaResponse.builder()
                                        .build()));
                    }
                    return Mono.just(ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                            .body(PersonaResponse.builder().build()));
                });
    }
    
    @GetMapping("/consultarpersona")
    public Mono<ResponseEntity<PersonaResponse>> consultarPersona(
            @RequestParam("numeroIdentificacion") String numeroIdentificacion) {
        return personaService.consultarPersona(numeroIdentificacion)
                .map(ResponseEntity::ok)
                .onErrorResume(error -> {
                    if (error.getMessage() != null && error.getMessage().contains("No se encontró")) {
                        return Mono.just(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
                    }
                    return Mono.just(ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build());
                });
    }
}

