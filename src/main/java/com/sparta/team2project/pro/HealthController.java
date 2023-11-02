package com.sparta.team2project.pro;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class HealthController {

    @GetMapping("/health")
    public String checkHealth() {
        return "healthy";
    }
}


