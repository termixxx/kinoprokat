package ru.vsu.kinoprokat.rental;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.vsu.kinoprokat.movie.MovieEntity;
import ru.vsu.kinoprokat.movie.MovieRepository;
import ru.vsu.kinoprokat.user.UserEntity;
import ru.vsu.kinoprokat.user.UserRepository;

import java.time.LocalDate;

@Service
@RequiredArgsConstructor
public class RentalService {

    private final RentalRepository rentalRepository;
    private final UserRepository userRepository;
    private final MovieRepository movieRepository;

    public RentalDto create(CreateRentalDto dto) {

        UserEntity user = userRepository.findById(dto.userId())
                .orElseThrow();

        MovieEntity movie = movieRepository.findById(dto.movieId())
                .orElseThrow();

        RentalEntity rental = RentalEntity.builder()
                .user(user)
                .movie(movie)
                .rentalDate(LocalDate.now())
                .build();

        rental = rentalRepository.save(rental);

        return new RentalDto(
                rental.getId(),
                user.getId(),
                user.getFirstName() + " " + user.getLastName(),
                movie.getId(),
                movie.getTitle(),
                rental.getRentalDate()
        );
    }
}
