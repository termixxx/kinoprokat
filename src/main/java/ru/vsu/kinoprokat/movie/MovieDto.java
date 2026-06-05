package ru.vsu.kinoprokat.movie;

import lombok.Data;

@Data
public class MovieDto {

    private String title;

    private String genre;

    private Integer releaseYear;
}
