package com.directa24.directa24_back_end_dev_challenge.controller;

import java.util.Collections;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.directa24.directa24_back_end_dev_challenge.dto.DirectorsResponseDto;
import com.directa24.directa24_back_end_dev_challenge.service.MovieService;

import jakarta.validation.constraints.Min;
import lombok.extern.slf4j.Slf4j;
@RestController
@Slf4j
@RequestMapping("/api/v1")
public class MovieController {

    private MovieService movieService;
    
    public MovieController(MovieService movieService){
        this.movieService = movieService;
    }

    private static final Logger logger = LoggerFactory.getLogger(MovieController.class);
    
    @GetMapping("/directors")
    public ResponseEntity<DirectorsResponseDto> getDirectors(@RequestParam @Min(1) int threshold) {
    if (threshold < 1) {
        throw new IllegalArgumentException("Threshold must be greater than or equal to 1");
    }

    try {
        logger.info("Returning directors list with threshold: {} ", threshold);
        return ResponseEntity.ok(new DirectorsResponseDto(movieService.getDirectors(threshold)));
    } catch (Exception e) {
        logger.error("Error getting directors", e);
        return ResponseEntity.internalServerError().body(new DirectorsResponseDto(Collections.emptyList()));
    }
}
}
