package com.techflow.techflow.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/protected")
@RestController
public class ProtectedController {

    @GetMapping("/info")
    public ResponseEntity<String> getProtectedInfo() {
        return new ResponseEntity<>("Cette route est protégée.", HttpStatus.OK);
    }
}