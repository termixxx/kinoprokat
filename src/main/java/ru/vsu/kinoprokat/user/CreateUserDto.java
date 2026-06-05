package ru.vsu.kinoprokat.user;

public record CreateUserDto(
        String firstName,
        String lastName,
        String email
) {
}
