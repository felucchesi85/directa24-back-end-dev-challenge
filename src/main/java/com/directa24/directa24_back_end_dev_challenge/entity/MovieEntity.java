package com.directa24.directa24_back_end_dev_challenge.entity;


import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "MovieEntity")
public class MovieEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JsonProperty("title")
    @NotBlank(message = "Title is required")
    private String title;

    @JsonProperty("year")
    @NotBlank(message = "Year is required")
    private String year;

    @JsonProperty("rated")
    private String rated;

    @JsonProperty("released")
    private String released;

    @JsonProperty("runtime")
    private String runtime;

    @JsonProperty("genre")
    private String genre;

    @JsonProperty("director")
    private String director;

    @JsonProperty("writer")
    private String writer;

    @JsonProperty("actors")
    private String actors;
}