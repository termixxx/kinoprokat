package ru.vsu.kinoprokat.rental;

public record CreateRentalDto(
        Long userId,
        Long movieId
) {
}
