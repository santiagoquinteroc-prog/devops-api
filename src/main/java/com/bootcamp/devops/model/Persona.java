package com.bootcamp.devops.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table("devops_schema.persona")
public class Persona {
    
    @Id
    @Column("id")
    private Long id;
    
    @Column("numero_identificacion")
    private String numeroIdentificacion;
    
    @Column("nombre")
    private String nombre;
    
    @Column("email")
    private String email;
}

