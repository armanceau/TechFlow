package com.techflow.techflow.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/public")
@RestController
public class PublicController {
    @GetMapping("/info")
    public ResponseEntity<String> getPublicInfo() {
        return new ResponseEntity<>("Cette route est publique.", HttpStatus.OK);
    }
}
