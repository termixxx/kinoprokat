package ru.vsu.kinoprokat.rental;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.vsu.kinoprokat.movie.MovieEntity;
import ru.vsu.kinoprokat.movie.MovieRepository;
import ru.vsu.kinoprokat.user.UserEntity;
import ru.vsu.kinoprokat.user.UserRepository;

import java.time.LocalDate;
import java.util.NoSuchElementException;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class RentalServiceTest {

    @Mock
    private RentalRepository rentalRepository;

    @Mock
    private UserRepository userRepository;

    @Mock
    private MovieRepository movieRepository;

    @InjectMocks
    private RentalService rentalService;

    @Test
    void shouldCreateRental() {

        UserEntity user = UserEntity.builder()
                .id(1L)
                .firstName("Ivan")
                .lastName("Ivanov")
                .build();

        MovieEntity movie = MovieEntity.builder()
                .id(10L)
                .title("Matrix")
                .build();

        RentalEntity rental = RentalEntity.builder()
                .id(100L)
                .user(user)
                .movie(movie)
                .rentalDate(LocalDate.now())
                .build();

        when(userRepository.findById(1L))
                .thenReturn(Optional.of(user));

        when(movieRepository.findById(10L))
                .thenReturn(Optional.of(movie));

        when(rentalRepository.save(any(RentalEntity.class)))
                .thenReturn(rental);

        RentalDto result = rentalService.create(
                new CreateRentalDto(1L, 10L)
        );

        assertThat(result.id()).isEqualTo(100L);
        assertThat(result.userId()).isEqualTo(1L);
        assertThat(result.movieId()).isEqualTo(10L);
        assertThat(result.userName()).isEqualTo("Ivan Ivanov");
        assertThat(result.movieTitle()).isEqualTo("Matrix");
    }

    @Test
    void shouldThrowWhenUserNotFound() {

        when(userRepository.findById(1L))
                .thenReturn(Optional.empty());

        assertThatThrownBy(() ->
                rentalService.create(new CreateRentalDto(1L, 10L))
        ).isInstanceOf(NoSuchElementException.class);
    }

    @Test
    void shouldThrowWhenMovieNotFound() {

        UserEntity user = UserEntity.builder()
                .id(1L)
                .build();

        when(userRepository.findById(1L))
                .thenReturn(Optional.of(user));

        when(movieRepository.findById(10L))
                .thenReturn(Optional.empty());

        assertThatThrownBy(() ->
                rentalService.create(new CreateRentalDto(1L, 10L))
        ).isInstanceOf(NoSuchElementException.class);
    }
}