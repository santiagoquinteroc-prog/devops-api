package com.bootcamp.devops.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelpController {
    
    private static final Logger logger = LoggerFactory.getLogger(HelpController.class);
    
    @GetMapping("/help")
    public ResponseEntity<String> help() {
        return ResponseEntity.ok("Help");
    }
    @GetMapping("/health/alerts-test")
    public ResponseEntity<String> alertsTest() {
        logger.error("ERROR prueba alerta CloudWatch");
        logger.warn("WARN prueba alerta CloudWatch");
        return ResponseEntity.ok("Logs de prueba generados para validar alertas CloudWatch");
    }
}


