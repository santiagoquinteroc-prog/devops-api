CREATE SCHEMA IF NOT EXISTS devops_schema;

SET search_path TO devops_schema;

DROP TABLE IF EXISTS devops_schema.persona;

CREATE TABLE devops_schema.persona (
    id BIGSERIAL PRIMARY KEY,
    numero_identificacion VARCHAR(50) NOT NULL UNIQUE,
    nombre VARCHAR(255) NOT NULL,
    email VARCHAR(255) NOT NULL,
    CONSTRAINT email_unique UNIQUE (email)
);

CREATE INDEX idx_persona_numero_identificacion 
ON devops_schema.persona(numero_identificacion);
