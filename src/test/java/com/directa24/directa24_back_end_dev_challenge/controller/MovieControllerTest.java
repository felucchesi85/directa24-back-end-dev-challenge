package com.directa24.directa24_back_end_dev_challenge.controller;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

import java.util.Collections;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.directa24.directa24_back_end_dev_challenge.dto.DirectorsResponseDto;
import com.directa24.directa24_back_end_dev_challenge.service.MovieService;

class MovieControllerTest {
    private MovieController movieController;

    @Mock
    private MovieService movieService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
        movieController = new MovieController(movieService);
    }

    @SuppressWarnings("null")
    @Test
    void shouldReturnDirectorsWhenValidThresholdIsProvided() {
        // Mocking the behavior of movieService.getDirectors()
        when(movieService.getDirectors(10)).thenReturn(Collections.singletonList("Director 1"));
    
        // Calling the controller method
        ResponseEntity<DirectorsResponseDto> response = movieController.getDirectors(10);
    
        // Assertions
        assert response.getStatusCode().equals(HttpStatus.OK);
        assert response.getBody().getDirectors().size() == 1;
        assert response.getBody().getDirectors().get(0).equals("Director 1");
        assertNotNull(response.getBody().getDirectors());
    }
    
}
