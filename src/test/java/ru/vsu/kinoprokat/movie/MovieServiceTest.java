package ru.vsu.kinoprokat.movie;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class MovieServiceTest {
    @Mock
    private MovieRepository movieRepository;

    @InjectMocks
    private MovieService movieService;

    @Test
    void shouldReturnAllMovies() {
        List<MovieEntity> movies = List.of(
                MovieEntity.builder().id(1L).title("Matrix").build(),
                MovieEntity.builder().id(2L).title("Avatar").build()
        );

        when(movieRepository.findAll()).thenReturn(movies);

        List<MovieEntity> result = movieService.getAll();

        assertThat(result).hasSize(2);
        assertThat(result.getFirst().getTitle()).isEqualTo("Matrix");

        verify(movieRepository).findAll();
    }

    @Test
    void shouldSaveMovie() {
        MovieEntity movie = MovieEntity.builder()
                .title("Interstellar")
                .build();

        when(movieRepository.save(movie)).thenReturn(movie);

        MovieEntity result = movieService.save(movie);

        assertThat(result.getTitle()).isEqualTo("Interstellar");

        verify(movieRepository).save(movie);
    }
}