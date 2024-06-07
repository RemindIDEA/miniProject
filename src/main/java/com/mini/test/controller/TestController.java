package com.mini.test.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {
    @GetMapping("/health")
    public ResponseEntity<String> healthCheck() {
        System.out.println("체크");
        return ResponseEntity.ok("OK");
    }
}
