package com.bootcamp.devops.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelpController {
    
    @GetMapping("/help")
    public ResponseEntity<String> help() {
        return ResponseEntity.ok("Help");
    }
}


