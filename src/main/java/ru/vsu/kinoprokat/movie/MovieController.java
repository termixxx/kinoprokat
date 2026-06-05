package ru.vsu.kinoprokat.movie;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/movies")
@RequiredArgsConstructor
public class MovieController {

    private final MovieService movieService;

    @GetMapping
    public List<MovieEntity> getAll() {
        return movieService.getAll();
    }

    @PostMapping
    public MovieEntity create(@RequestBody MovieEntity movie) {
        return movieService.save(movie);
    }
}
