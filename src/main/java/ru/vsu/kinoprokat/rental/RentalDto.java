package ru.vsu.kinoprokat.rental;

import java.time.LocalDate;

public record RentalDto(
        Long id,
        Long userId,
        String userName,
        Long movieId,
        String movieTitle,
        LocalDate rentalDate
) {
}
