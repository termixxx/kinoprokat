package ru.vsu.kinoprokat.movie;

import lombok.Data;

@Data
public class MovieCreateDto {

    private String title;

    private String genre;

    private Integer releaseYear;
}
