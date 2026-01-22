package com.bootcamp.devops.repository;

import com.bootcamp.devops.model.Persona;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

@Repository
public interface PersonaRepository extends ReactiveCrudRepository<Persona, Long> {
    
    Mono<Persona> findByNumeroIdentificacion(String numeroIdentificacion);
}

