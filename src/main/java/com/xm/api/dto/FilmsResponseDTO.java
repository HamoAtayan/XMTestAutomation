package com.xm.api.dto;

import lombok.Data;

import java.util.List;

@Data
public class FilmsResponseDTO {
    private int count;
    private String next;
    private String previous;
    private List<FilmDto> results;
}
