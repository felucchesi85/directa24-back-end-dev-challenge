package com.directa24.directa24_back_end_dev_challenge.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class DirectorsResponseDto {
    @JsonProperty("directors_name")
    private List<String> directors;

}