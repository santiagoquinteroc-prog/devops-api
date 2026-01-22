package com.bootcamp.devops.service;

import com.bootcamp.devops.dto.PersonaRequest;
import com.bootcamp.devops.dto.PersonaResponse;
import com.bootcamp.devops.model.Persona;
import com.bootcamp.devops.repository.PersonaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class PersonaService {
    
    private final PersonaRepository personaRepository;
    
    public Mono<PersonaResponse> guardarPersona(PersonaRequest request) {
        Persona persona = Persona.builder()
                .numeroIdentificacion(request.getNumeroIdentificacion())
                .nombre(request.getNombre())
                .email(request.getEmail())
                .build();
        
        return personaRepository.findByNumeroIdentificacion(request.getNumeroIdentificacion())
                .hasElement()
                .flatMap(exists -> {
                    if (exists) {
                        return Mono.<PersonaResponse>error(new RuntimeException(
                                "La persona con número de identificación " + request.getNumeroIdentificacion() + " ya existe"));
                    }
                    return personaRepository.save(persona)
                            .map(this::toResponse);
                });
    }
    
    public Mono<PersonaResponse> consultarPersona(String numeroIdentificacion) {
        return personaRepository.findByNumeroIdentificacion(numeroIdentificacion)
                .map(this::toResponse)
                .switchIfEmpty(Mono.error(new RuntimeException(
                        "No se encontró una persona con número de identificación: " + numeroIdentificacion)));
    }
    
    private PersonaResponse toResponse(Persona persona) {
        return PersonaResponse.builder()
                .numeroIdentificacion(persona.getNumeroIdentificacion())
                .nombre(persona.getNombre())
                .email(persona.getEmail())
                .build();
    }
}

