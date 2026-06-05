package ru.vsu.kinoprokat.movie;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MovieService {

    private final MovieRepository movieRepository;

    public List<MovieEntity> getAll() {
        return movieRepository.findAll();
    }

    public MovieEntity save(MovieEntity movie) {
        return movieRepository.save(movie);
    }
}
