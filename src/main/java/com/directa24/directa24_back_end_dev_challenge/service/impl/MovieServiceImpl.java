package com.directa24.directa24_back_end_dev_challenge.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.directa24.directa24_back_end_dev_challenge.dto.MovieDto;
import com.directa24.directa24_back_end_dev_challenge.dto.ResponseDto;
import com.directa24.directa24_back_end_dev_challenge.service.MovieService;

@Service
public class MovieServiceImpl implements MovieService {

    private final RestTemplate restTemplate;
    private static final Logger logger = LoggerFactory.getLogger(MovieServiceImpl.class);

    public MovieServiceImpl(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    /**
     * Retrieves a list of directors
     * @param threshold the minimum number of movies a director must have
     * @return a list of director names
     */
    @Override
    public List<String> getDirectors(int threshold) {
        Map<String, Integer> directorMovies = new HashMap<>();
        fetchMovies(directorMovies);
        return filterDirectorsByThreshold(directorMovies, threshold);
    }

    /**
     * Fetches movie data
     * @param directorMovies the count of movies for each director.
     */
    private void fetchMovies(Map<String, Integer> directorMovies) {
        int page = 2;
        ObjectMapper objectMapper = new ObjectMapper();

        try {
            String apiUrl = String.format("https://eron-movies.wiremockapi.cloud/api/movies/search?page=%d", page);
            logger.info("Fetching data from: {}", apiUrl);

            ResponseEntity<String> responseEntity = restTemplate.getForEntity(apiUrl, String.class);

            if (!responseEntity.getStatusCode().is2xxSuccessful()) {
                logger.error("Failed to fetch data: {}", responseEntity.getStatusCode());
                return;
            }

            if (responseEntity.getBody() != null) {
                ResponseDto response = objectMapper.readValue(responseEntity.getBody(), ResponseDto.class);
                processMovies(response.getData(), directorMovies);
            } else {
                logger.error("Empty response body.");
            }
        } catch (Exception e) {
            logger.error("Error retrieving movie data", e);
        }
    }

    /**
     * Processes a list of movies 
     * @param movies a list of MovieDto 
     * @param directorMovies the count of movies for each director
     */
    private void processMovies(List<MovieDto> movies, Map<String, Integer> directorMovies) {
        logger.info("Processing movies: {}", movies);
        movies.stream()
                .map(MovieDto::getDirector)
                .forEach(director -> directorMovies.merge(director, 1, Integer::sum));
        logger.info("Processed movies: {}", directorMovies);
    }

    /**
     * Filters directors based on the threshold of movies
     * @param directorMovies the count of movies for each director
     * @param threshold the minimum number of movies a director must have
     * @return a sorted list of director who have directed more movies than the threshold.
     */
    private List<String> filterDirectorsByThreshold(Map<String, Integer> directorMovies, int threshold) {
        logger.info("Filtering directors by threshold: {}", threshold);
        List<String> filteredDirectors = directorMovies.entrySet().stream()
                .filter(entry -> entry.getValue() > threshold)
                .map(Map.Entry::getKey)
                .sorted()
                .toList();
        logger.info("Filtered directors: {}", filteredDirectors);
        return filteredDirectors;
    }
}
